(ns cells.db)

(def default-db
  {:name "Cellular Automata"
   :host "cellular-automata"
   :grid-types #{:matrix :flat :map}
   :simulation {:count 1
                :games #{:game-of-life :day-and-night}
                :current {:game :game-of-life
                          :grid :matrix
                          :rows 16
                          :cols 16
                          :density 0.4}}
   :cells {:panel-size {:width 300
                        :height 300}
           :cell-size 20
           :frame-rate 1}})
