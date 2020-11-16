(ns cells.views
  (:require [re-frame.core :as re-frame]
            [cells.subs :as subs]
            [cells.events :as events]))

(defn simulation-info-panel []
  (let [current @(re-frame/subscribe [::subs/current-simulation])
        simulation-count @(re-frame/subscribe [::subs/simulation-count])
        panel @(re-frame/subscribe [::subs/cells-panel])
        panel-size (panel :panel-size)]
    [:table {:class "table table-sm table-striped"}
     [:tbody
      [:tr
       [:td "Simulation:"]
       [:td simulation-count]]
      [:tr
       [:td "Type:"]
       [:td (-> current :type name (clojure.string/replace #"-" " "))]]
      [:tr
       [:td "Grid model:"]
       [:td (-> current :grid name)]]
      [:tr
       [:td "(rows, cols)"]
       [:td (str "(" (current :rows) ", " (current :cols) ")")]]
      [:tr
       [:td "Density:"]
       [:td (current :density)]]
      [:tr
       [:td "(Width, Height)"]
       [:td (str "(" (panel-size :width) ", " (panel-size :height) ")")]]
      [:tr
       [:td "Cell Size"]
       [:td (panel :cell-size)]]]]))

(defn cells-panel []
  [:div {:style {:width (str @(re-frame/subscribe [::subs/cells-panel-width]) "px")
                 :height (str @(re-frame/subscribe [::subs/cells-panel-height]) "px")
                 :border "1px solid black"}}])

(defn simulation-reset-button []
  [:button {:class "btn btn-primary btn-block"
            :type :button
            :on-click #(re-frame/dispatch [::events/reset])}
   "New Simulation"])

(defn selector [label default options event]
  (let [id (clojure.string/replace label #" " "-")]
    [:div {:class "input-group mb-3"}
     [:div {:class "input-group-prepend"}
      [:label {:class "input-group-text" :for id} label]]
     [:select {:class "custom-select"
               :id id
               :name id
               :default-value default
               :on-change
               (fn [e]
                 (.preventDefault e)
                 (re-frame/dispatch [event (.. e -target -value)]))}
      (for [[option idx] (map vector options (range))]
        [:option {:value option :key idx}
         (-> option name (clojure.string/replace #"-" " "))])]]))

(defn simulation-type-selector []
  (let [simulation-types @(re-frame/subscribe [::subs/simulation-types])
        current-type (:type @(re-frame/subscribe [::subs/current-simulation]))]
    [selector "Simulation Type" current-type simulation-types
     ::events/change-simulation-type]))

(defn grid-type-selector []
  (let [grid-types @(re-frame/subscribe [::subs/grid-types])
        current-type (:grid @(re-frame/subscribe [::subs/current-simulation]))]
    [selector "Grid Type" current-type grid-types
     ::events/change-grid-type]))

(defn numeric-input [label value min max step event]
  [:div {:class "input-group mb-3"}
   [:div {:class "input-group-prepend"}
    [:span {:class "input-group-text"} label]]
   [:input {:class "form-control"
            :type :number
            :value value
            :min min
            :max max
            :step step
            :on-change (fn [e]
                         (.preventDefault e)
                         (re-frame/dispatch
                          [event (.. e -target -value)]))}]])

(defn grid-size-input []
  (let [[rows cols] ((juxt :rows :cols)
                     @(re-frame/subscribe [::subs/current-simulation]))]
    [:div {:class "row"}
     [:div {:class "col-sm"}
      [numeric-input "Rows" rows "2" "100" "1" ::events/change-grid-rows]]
     [:div {:class "col-sm"}
      [numeric-input "Cols" cols "2" "100" "1" ::events/change-grid-cols]]]))

(defn simulation-density-input []
  (let [current @(re-frame/subscribe [::subs/current-simulation])]
    [numeric-input "Density" (current :density) "0.0" "1.0" "0.1"
     ::events/change-simulation-density]))

(defn panel-size-input []
  (let [current (:panel-size @(re-frame/subscribe [::subs/cells-panel]))]
    [:div {:class "row"}
     [:div {:class "col-sm"}
      [numeric-input "Panel Width" (current :width) "100" "2000" "1"
       ::events/change-panel-width]]
     [:div {:class "col-sm"}
      [numeric-input "Panel Height" (current :height) "100" "2000" "1"
      ::events/change-panel-height]]]))

(defn cell-size-input []
  (let [current @(re-frame/subscribe [::subs/cells-panel])]
    [numeric-input "Cell Size" (current :cell-size) "1" "50" "1"
      ::events/change-cell-size]))

(defn simulation-panel []
  [:div {:class "container-fluid"}
   [:div {:class "row"}
    [:div {:class "col-md mb-3"}
     [cells-panel]]
    [:div {:class "col-md"}
     [:div {:class "row"}
      [:div {:class "col-sm mb-3"}
       [simulation-reset-button]]]
     [:div {:class "row"}
      [:div {:class "col-sm"}
       [simulation-type-selector]]]
     [:div {:class "row"}
      [:div {:class "col-sm"}
       [grid-type-selector]]]
     [:div {:class "row"}
      [:div {:class "col-sm"}
       [grid-size-input]]]
     [:div {:class "row"}
      [:div {:class "col-sm"}
       [simulation-density-input]]]
     [:div {:class "row"}
      [:div {:class "col-sm"}
       [panel-size-input]]]
     [:div {:class "row"}
      [:div {:class "col-sm"}
       [cell-size-input]]]
     [:div {:class "row"}
      [:div {:class "col-sm"}
       [simulation-info-panel]]]]]])

(defn main-panel []
  (let [name @(re-frame/subscribe [::subs/name])]
    [:div
     [:h1 {:class "display-4"} name]
     [simulation-panel]]))
