(ns cells.views
  (:require-macros [cells.macros :refer [row tr prepended-input]])
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
      (tr "Simulation" simulation-count)
      (tr "Game" (-> current :game name (clojure.string/replace #"-" " ")))
      (tr "Grid Model" (-> current :grid name))
      (tr "Grid Size" (str (current :rows) " by " (current :cols)))
      (tr "Grid Resolution" (str (panel-size :width) "x" (panel-size :height) " pixels"))
      (tr "Density" (current :density))
      (tr "Cell Size" (str (panel :size) "x" (panel :size) " pixels"))]]))

(defn cells-panel []
  (let [host @(re-frame/subscribe [::subs/host])]
    [:div
     {:id host
      :style
      {:width (str (+ 2 @(re-frame/subscribe [::subs/cells-panel-width])) "px")
       :height (str (+ 2 @(re-frame/subscribe [::subs/cells-panel-height])) "px")
       :border "1px solid black"}}]))

(defn simulation-reset-button []
  [:button {:class "btn btn-primary btn-block"
            :type :button
            :on-click #(re-frame/dispatch [::events/reset])}
   "New Simulation"])

(defn selector [label default options event]
  (let [id (clojure.string/replace label #" " "-")]
    (prepended-input
     label
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
         (-> option name (clojure.string/replace #"-" " "))])])))

(defn simulation-game-selector []
  (let [simulation-games @(re-frame/subscribe [::subs/simulation-games])
        current-game (:game @(re-frame/subscribe [::subs/current-simulation]))]
    [selector "Simulated Game" current-game simulation-games ::events/change-game]))

(defn grid-type-selector []
  (let [grid-types @(re-frame/subscribe [::subs/grid-types])
        current-type (:grid @(re-frame/subscribe [::subs/current-simulation]))]
    [selector "Grid Type" current-type grid-types ::events/change-grid-type]))

(defn numeric-input [label value min max step event]
  (prepended-input
   label
   [:input {:class "form-control"
            :type :number
            :value value
            :min min
            :max max
            :step step
            :on-change (fn [e]
                         (.preventDefault e)
                         (re-frame/dispatch
                          [event (.. e -target -value)]))}]))

(defn grid-size-input []
  (let [[rows cols] ((juxt :rows :cols)
                     @(re-frame/subscribe [::subs/current-simulation]))]
    (row [numeric-input "Rows" rows "2" "100" "1" ::events/change-grid-rows]
         [numeric-input "Cols" cols "2" "100" "1" ::events/change-grid-cols])))

(defn simulation-density-input []
  (let [current @(re-frame/subscribe [::subs/current-simulation])]
    [numeric-input "Density" (current :density) "0.0" "1.0" "0.1"
     ::events/change-density]))

(defn panel-size-input []
  (let [current (:panel-size @(re-frame/subscribe [::subs/cells-panel]))]
    (row [numeric-input "Panel Width" (current :width) "100" "2000" "1"
          ::events/change-panel-width]
         [numeric-input "Panel Height" (current :height) "100" "2000" "1"
          ::events/change-panel-height])))

(defn cell-size-input []
  (let [current @(re-frame/subscribe [::subs/cells-panel])]
    [numeric-input "Cell Size" (current :size) "1" "50" "1"
     ::events/change-cell-size]))

(defn cell-fps-input []
  (let [current @(re-frame/subscribe [::subs/cells-panel])]
    [numeric-input "Frame rate" (current :frame-rate) "1" "200" "1"
     ::events/change-frame-rate]))

(defn simulation-panel []
  [:div {:class "container-fluid"}
   [:div {:class "row"}
    [:div {:class "col-md mb-3"}
     [cells-panel]]
    [:div {:class "col-md"}
     (row [simulation-reset-button])
     (row [simulation-game-selector])
     (row [grid-type-selector])
     (row [grid-size-input])
     (row [simulation-density-input])
     (row [panel-size-input])
     (row [cell-size-input]
          [cell-fps-input])
     (row [simulation-info-panel])]]])

(defn main-panel []
  (let [name @(re-frame/subscribe [::subs/name])]
    [:div {:class :container-fluid}
     (row [:h1 {:class :display-4} name])
     (row [:a {:class :lead
               :href "https://bitbucket.org/ahlk/cellular-automata/src/master/"}
           "View the Source Code"])
     [:div {:class :row}
      [simulation-panel]]]))
