(ns cells.db)

(def default-db
  {:name "Cellular Automata"
   :grid-types #{:matrix :flat :map}
   :simulation {:count 1
                :types #{:game-of-life :day-and-night}
                :current {:type :game-of-life
                          :grid :matrix
                          :rows 16
                          :cols 16
                          :density 0.4}}
   :cells {:panel-size {:width 300
                        :height 300}
           :cell-size 20
           :frame-rate 1}})
