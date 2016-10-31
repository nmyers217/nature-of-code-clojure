(ns nature-of-code-clojure.accel-towards-mouse
  (:require [quil.core :as q]))

(defn setup
  "Sets up the simulation, and returns the data for a ball"
  []
  {:position [(/ (q/width) 2) (/ (q/height) 2)]
   :velocity [0 0]
   :size 50})

(defn normalize
  "Normalizes a vector to a length of 1"
  [vec]
  (let [length (apply q/mag vec)]
    (map #(/ % length) vec)))

(defn update-state
  "Accelerates the ball using a scaled vector pointing towards the mouse"
  [ball]
  (let [{:keys [position velocity]} ball
        ball-to-mouse-vec (map - [(q/mouse-x) (q/mouse-y)] position)
        accel-vec (map #(* % 0.05) (normalize ball-to-mouse-vec))]
    (assoc ball :velocity (map + velocity accel-vec)
                :position (map + position velocity))))

(defn draw-state
  "Draws the ball, and a line from its position to the mouse"
  [ball]
  (q/background 51)
  (let [{[x y] :position size :size} ball]
    (q/fill 127 0 255)
    (q/no-stroke)
    (q/ellipse x y size size)
    (q/stroke 0 127 255)
    (q/line x y (q/mouse-x) (q/mouse-y))))
