(ns cells.game.grid.map
  (:require [cells.game.grid :refer [make transform get-cell]]
            [cells.game.util :as util]))

(defmethod transform :map [grid f]
  (into {}
        (comp (map (fn [[r c]] [[r c] (f [grid r c])]))
              (filter (comp true? second)))
        (:coords grid)))

(defmethod make :map [{:keys [rows cols density]}]
  (let [opts {:type :map
              :rows rows
              :cols cols
              :coords (util/combinations-of (range rows) (range cols))}]
    (merge opts
           {:cells (transform opts (fn [[_ r c]] (< (rand) density)))})))

(defmethod get-cell :map [{:keys [cells]} r c] (get cells [r c] false))


