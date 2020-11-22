(ns cells.macros)

;; View macros
(defmacro row [& body]
  `[:div {:class :row}
    ~@(map (fn [c] [:div {:class :col-sm} c])
           body)])

(defmacro tr [& tds]
  `[:tr ~@(map (fn [c] [:td c]) tds)])

(defmacro prepended-input [label input]
  `[:div {:class "input-group mb-3"}
    [:div {:class "input-group-prepend"}
     [:label {:class "input-group-text"} ~label]]
    ~input])

;; Event macros
(defmacro reg-config-event [id keys f]
  `(re-frame/reg-event-fx
      ~id
      (fn [cofx# [event# config#]]
        (~'reset-simulation-with cofx# ~keys (~f config#)))))
