(ns cells.game.day-and-night
  (:require [quil.core :as q]
            [cells.game.automata
             :refer [evolve setup update-state life-thresholds-for]]))

(defmethod life-thresholds-for :day-and-night [_]
  {:living #{3 4 6 7 8} :dead #{3 6 7 8}})

(defmethod update-state :day-and-night [game]
  (assoc-in game [:grid :cells] (evolve game)))

(defmethod setup :day-and-night [{:keys [grid size frame-rate]}]
  (fn []
    (q/smooth)
    (q/frame-rate frame-rate)
    (q/background 255)
    {:type :day-and-night
     :size size
     :grid grid}))
