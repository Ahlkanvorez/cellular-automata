(ns cells.game.core
  (:require [cells.game.draw :as draw]
            [cells.game.grid :as grid]
            [cells.game.grid.atom-matrix]
            [cells.game.grid.blinker]
            [cells.game.grid.flat]
            [cells.game.grid.gosper-glider-gun]
            [cells.game.grid.map]
            [cells.game.grid.matrix]
            [cells.game.grid.tumbler]))

(defn make-game [{:keys [game rows cols size grid density frame-rate]}]
  {:type game
   :frame-rate frame-rate
   :size size
   :grid (grid/make {:type grid :rows rows :cols cols :density density})})

(defn ^:export run [config]
  (draw/game (:host config) (:panel-size config) (make-game config)))
