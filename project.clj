(defproject cells "1.0.0"
  :description "A configurable webapp simulating Conway's Game of Life, and the Day & Night variation."
  :url "https://cellular-automata.robertm.io"
  :license {:name "BSD 3-Clause License"
            :url "https://choosealicense.com/licenses/bsd-3-clause/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [quil "3.1.0"]
                 [reagent "1.0.0"]
                 [re-frame "1.1.2"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.20"]]
  :hooks [leiningen.cljsbuild]
  :profiles {:dev {:dependencies [[cider/piggieback "0.5.0"]
                                  [figwheel-sidecar "0.5.20"]]
                   :source-paths ["cljs_src"]
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}}}
  :clean-targets ^{:protect false} ["resources/public/js"]
  :cljsbuild
  {:builds [; development build with figwheel hot swap
            {:id "development"
             :source-paths ["src"]
             :figwheel true
             :compiler
             {:main "cells.core"
              :output-to "resources/public/js/main.js"
              :output-dir "resources/public/js/development"
              :asset-path "js/development"}}
            ; minified and bundled build for deployment
            {:id "optimized"
             :source-paths ["src"]
             :compiler
             {:main "cells.core"
              :output-to "resources/public/js/main.js"
              :output-dir "resources/public/js/optimized"
              :asset-path "js/optimized"
              :optimizations :advanced}}]})
