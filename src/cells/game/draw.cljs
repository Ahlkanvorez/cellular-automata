(ns cells.game.draw
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [cells.game.automata :as automata]
            [cells.game.grid :as grid]))

(defn grid [{:keys [grid size]}]
  (doseq [r (range (:rows grid))
          c (range (:cols grid))]
    (q/stroke 133)
    (if (grid/get-cell grid r c)
      (q/fill 0)
      (q/fill 133))
    (q/rect (* size c) (* size r) size size)))

(defn game [host panel-size g]
  (q/defsketch cellular-automata
    :host host
    :size [(panel-size :width) (panel-size :height)]
    :setup (automata/setup g)
    :draw grid
    :update automata/update-state
    :middleware [m/fun-mode]
    :renderer :p2d))
