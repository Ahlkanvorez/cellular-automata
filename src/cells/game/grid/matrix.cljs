(ns cells.game.grid.matrix
  (:require [cells.game.grid :refer [make transform get-cell]]
            [cells.game.util :as util]))

(defmethod transform :matrix [grid f]
  (vec (for [r (range (:rows grid))]
         (vec (for [c (range (:cols grid))]
                (f [grid r c]))))))

(defmethod make :matrix [{:keys [rows cols density]}]
  {:cells (transform {:type :matrix :rows rows :cols cols}
                     (fn [[_ r c]] (< (rand) density)))
   :coords (util/combinations-of (range rows) (range cols))
   :rows rows
   :cols cols
   :type :matrix})

(defmethod get-cell :matrix [grid r c] (get-in grid [:cells r c]))
