(ns cells.db)

(def default-db
  {:name "Cellular Automata"
   :host "cellular-automata"
   :grid-types #{:matrix :flat :map :blinker :gosper-glider-gun :tumbler}
   :simulation {:count 1
                :games #{:game-of-life :day-and-night}
                :current {:game :game-of-life
                          :grid :gosper-glider-gun
                          :rows 15
                          :cols 15
                          :density 0.4}}
   :cells {:panel-size {:width 750
                        :height 550}
           :size 20
           :frame-rate 15}})
