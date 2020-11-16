(ns cells.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub ::name :name)

(re-frame/reg-sub ::cells-panel :cells)
(re-frame/reg-sub ::cells-panel-width (comp :width :panel-size :cells))
(re-frame/reg-sub ::cells-panel-height (comp :height :panel-size :cells))

(re-frame/reg-sub ::simulation-count (comp :count :simulation))

(re-frame/reg-sub ::simulation-types (comp :types :simulation))

(re-frame/reg-sub ::current-simulation (comp :current :simulation))

(re-frame/reg-sub ::grid-types :grid-types)
