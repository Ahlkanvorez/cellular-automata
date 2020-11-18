(ns cells.game.automata
  (:require [cells.game.grid :as grid]
            [cells.game.util :as util]))

(defmulti setup :type)
(defmulti update-state :type)
(defmulti life-thresholds-for :type)

(defonce diffs (util/combinations-of [-1 0 1] [-1 0 1]))

(defn neighbors [grid r c]
  (transduce (comp (remove #{[0 0]})
                   (map (fn [[dr dc]]
                          (grid/get-cell grid (+ r dr) (+ c dc))))
                   (filter true?)
                   (map int))
             +
             diffs))

(defn cell-status [grid r c]
  (if (grid/get-cell grid r c) :living :dead))

(defn evolve-cell [game]
  (fn [[grid r c]]
    (contains? (get (life-thresholds-for game) (cell-status grid r c))
               (neighbors grid r c))))

(defn evolve [game]
  (grid/transform (game :grid) (evolve-cell game)))
