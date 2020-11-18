(ns cells.game.grid.flat
  (:require [cells.game.grid :refer [make transform get-cell]]
            [cells.game.util :as util]))

(defmethod make :flat [{:keys [rows cols density]}]
  {:cells (->> (repeatedly #(if (< (rand) density) 1 0))
               (take (* rows cols))
               vec)
   :coords (util/combinations-of (range rows) (range cols))
   :rows rows
   :cols cols
   :type :flat})

(defmethod get-cell :flat [{:keys [cells rows cols]} r c]
  (when (and (< -1 r rows)
             (< -1 c cols))
    (= 1 (get cells (+ (* cols r) c)))))

(defmethod transform :flat [grid f]
  (transduce (map (fn [[r c]] (if (f [grid r c]) 1 0)))
             conj
             (grid :coords)))
