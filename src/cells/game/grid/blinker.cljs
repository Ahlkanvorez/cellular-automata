(ns cells.game.grid.blinker
  (:require [cells.game.grid :refer [make]]
            [cells.game.util :as util]))

(defmethod make :blinker [_]
  {:cells
   [0 0 0 0 0
    0 0 0 0 0
    0 1 1 1 0
    0 0 0 0 0
    0 0 0 0 0]
   :coords (util/combinations-of (range 5) (range 5))
   :rows 5
   :cols 5
   :type :flat})
