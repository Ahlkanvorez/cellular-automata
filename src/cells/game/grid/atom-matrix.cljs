(ns cells.game.grid.atom-matrix
  (:require [cells.game.grid :refer [make transform get-cell]]
            [cells.game.util :as util]))

(defn- get-cell-atom [grid r c]
  (-> (grid :cells) (nth r nil) (nth c nil)))

(defmethod transform :atom-matrix [grid f]
  (let [changes (atom [])]
    (dotimes [r (:rows grid)]
      (dotimes [c (:cols grid)]
        (when-let [cell (get-cell-atom grid r c)]
          (let [result (f [grid r c])]
            (when-not (= result @cell)
              (swap! changes conj [r c result]))))))
    (doseq [[r c v] @changes]
      (reset! (get-cell-atom grid r c) v)))
  (grid :cells))

(defmethod make :atom-matrix [{:keys [rows cols density]}]
  {:cells (transform {:type :matrix :rows rows :cols cols}
                     (fn [[_ r c]] (atom (< (rand) density))))
   :rows rows
   :cols cols
   :type :atom-matrix})

(defmethod get-cell :atom-matrix [grid r c]
  (when-let [cell (get-cell-atom grid r c)]
    @cell))
