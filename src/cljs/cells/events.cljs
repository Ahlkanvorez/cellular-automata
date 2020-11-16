(ns cells.events
  (:require [re-frame.core :as re-frame]
            [cells.db :as db]))

(re-frame/reg-event-db ::initialize-db (fn [_ _] db/default-db))

(re-frame/reg-event-db
 ::reset
 (fn [db _]
   (update-in db [:simulation :count] inc)))

(re-frame/reg-event-db
 ::change-simulation-type
 (fn [db [_event type]]
   (assoc-in db [:simulation :current :type] type)))

(re-frame/reg-event-db
 ::change-simulation-density
 (fn [db [_event type]]
   (assoc-in db [:simulation :current :density] type)))

(re-frame/reg-event-db
 ::change-grid-type
 (fn [db [_event type]]
   (assoc-in db [:simulation :current :grid] type)))

(re-frame/reg-event-db
 ::change-grid-rows
 (fn [db [_event type]]
   (assoc-in db [:simulation :current :rows] type)))

(re-frame/reg-event-db
 ::change-grid-cols
 (fn [db [_event type]]
   (assoc-in db [:simulation :current :cols] type)))

(re-frame/reg-event-db
 ::change-panel-width
 (fn [db [_event type]]
   (assoc-in db [:cells :panel-size :width] type)))

(re-frame/reg-event-db
 ::change-panel-height
 (fn [db [_event type]]
   (assoc-in db [:cells :panel-size :height] type)))

(re-frame/reg-event-db
 ::change-cell-size
 (fn [db [_event type]]
   (assoc-in db [:cells :cell-size] type)))
