(ns cells.game.game-of-life
  (:require [quil.core :as q :include-macros true]
            [cells.game.automata
             :refer [evolve setup update-state life-thresholds-for]]
            [cells.game.grid :as grid]
            [cells.game.util :as util]))

(defmethod life-thresholds-for :game-of-life [_] {:living #{2 3} :dead #{3}})

(defmethod update-state :game-of-life [game]
  (assoc-in game [:grid :cells] (evolve game)))

(defmethod setup :game-of-life [{:keys [grid size frame-rate]}]
  (fn []
    (q/smooth)
    (q/frame-rate frame-rate)
    (q/background 255)
    {:type :game-of-life
     :size size
     :grid grid}))
