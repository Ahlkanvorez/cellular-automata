(ns cells.game.core
  (:require [cells.game.draw :as draw]
            [cells.game.grid :as grid]
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
   :grid (grid/make {:type grid
                     :rows rows
                     :cols cols
                     :density density})})

(defn ^:export run [{:keys [host game rows cols grid density size panel-size frame-rate]}]
  (draw/game host
             panel-size
             (make-game {:game game
                         :rows rows
                         :cols cols
                         :grid grid
                         :density density
                         :size size
                         :frame-rate frame-rate})))
