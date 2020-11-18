(ns cells.game.grid.tumbler
  (:require [cells.game.grid :refer [make]]
            [cells.game.util :as util]))

(defmethod make :tumbler [_]
  {:cells
   [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 1 1 0 1 1 0 0 0 0 0
    0 0 0 0 0 0 1 1 0 1 1 0 0 0 0 0
    0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0
    0 0 0 0 0 1 0 1 0 1 0 1 0 0 0 0
    0 0 0 0 0 1 0 1 0 1 0 1 0 0 0 0
    0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
   :coords (util/combinations-of (range 16) (range 16))
   :rows 16
   :cols 16
   :type :flat})
