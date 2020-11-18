(ns cells.game.grid)

(defmulti make :type)
(defmulti transform (fn [g f] (:type g)))
(defmulti get-cell (fn [g r c] (:type g)))
