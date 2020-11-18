(ns cells.events
  (:require [re-frame.core :as re-frame]
            [cells.db :as db]
            [cells.game.core :as game]))

(defn db->simulation-config [db]
  (merge (-> db :simulation :current)
         (-> db :cells)
         (select-keys db [:host])))

(defn reset-simulation-with
  ([cofx]
   {:db (:db cofx)
    :restart-simulation (db->simulation-config (:db cofx))})
  ([cofx path value]
   (let [db (:db cofx)
         new-db ((if (fn? value) update-in assoc-in) db path value)]
     (reset-simulation-with (assoc cofx :db new-db)))))

(re-frame/reg-fx
 :restart-simulation
 (fn [config]
   (println (js/JSON.stringify (clj->js config)))
   (game/run config)))

(re-frame/reg-event-fx
 ::initialize-db
 (fn [cofx _]
   (reset-simulation-with (assoc cofx :db db/default-db))))

(re-frame/reg-event-fx
 ::reset
 (fn [cofx _]
   (reset-simulation-with cofx [:simulation :count] inc)))

(re-frame/reg-event-fx
 ::change-simulation-game
 (fn [cofx [_event game]]
   (reset-simulation-with cofx [:simulation :current :game] game)))

(re-frame/reg-event-fx
 ::change-simulation-density
 (fn [cofx [_event density]]
   (reset-simulation-with cofx [:simulation :current :density]
                          (if (string? density)
                            (js/parseFloat density)
                            density))))

(re-frame/reg-event-fx
 ::change-grid-type
 (fn [cofx [_event type]]
   (reset-simulation-with cofx [:simulation :current :grid] type)))

(re-frame/reg-event-fx
 ::change-grid-rows
 (fn [cofx [_event rows]]
   (reset-simulation-with cofx [:simulation :current :rows]
                          (if (string? rows)
                            (js/parseInt rows)
                            rows))))

(re-frame/reg-event-fx
 ::change-grid-cols
 (fn [cofx [_event cols]]
   (reset-simulation-with cofx [:simulation :current :cols]
                          (if (string? cols)
                            (js/parseInt cols)
                            cols))))

(re-frame/reg-event-fx
 ::change-panel-width
 (fn [cofx [_event width]]
   (reset-simulation-with cofx [:cells :panel-size :width]
                          (if (string? width)
                            (js/parseInt width)
                            width))))

(re-frame/reg-event-fx
 ::change-panel-height
 (fn [cofx [_event height]]
   (reset-simulation-with cofx [:cells :panel-size :height]
                          (if (string? height)
                            (js/parseInt height)
                            height))))

(re-frame/reg-event-fx
 ::change-cell-size
 (fn [cofx [_event size]]
   (reset-simulation-with cofx [:cells :cell-size]
                          (if (string? size)
                            (js/parseInt size)
                            size))))
