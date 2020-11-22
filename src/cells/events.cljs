(ns cells.events
  (:require-macros [cells.macros :refer [reg-config-event]])
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

(defn as-f [convert should-convert? s]
  (if (should-convert? s) (convert s) s))

(def as-int (partial as-f js/parseInt string?))
(def as-float (partial as-f js/parseFloat string?))

(re-frame/reg-fx :restart-simulation game/run)

(re-frame/reg-event-fx
 ::initialize-db
 (fn [cofx _] (reset-simulation-with (assoc cofx :db db/default-db))))

(re-frame/reg-event-fx
 ::reset
 (fn [cofx _] (reset-simulation-with cofx [:simulation :count] inc)))

(reg-config-event ::change-game [:simulation :current :game] keyword)

(reg-config-event ::change-density [:simulation :current :density] as-float)

(reg-config-event ::change-grid-type [:simulation :current :grid] keyword)

(reg-config-event ::change-grid-rows [:simulation :current :rows] as-int)

(reg-config-event ::change-grid-cols [:simulation :current :cols] as-int)

(reg-config-event ::change-panel-width [:cells :panel-size :width] as-int)

(reg-config-event ::change-panel-height [:cells :panel-size :height] as-int)

(reg-config-event ::change-cell-size [:cells :size] as-int)

(reg-config-event ::change-frame-rate [:cells :frame-rate] as-int)
