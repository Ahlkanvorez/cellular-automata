(ns cells.bootstrap)

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

