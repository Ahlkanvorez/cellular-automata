(ns cells.game.util)

(defn combinations-of [a b]
  (vec (mapcat (fn [x]
                 (map (fn [y] [x y])
                      b))
               a)))
