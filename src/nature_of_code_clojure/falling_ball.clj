(ns nature-of-code-clojure.falling-ball
  (:require [quil.core :as q]))

(defn setup
  "Return a ball's position velocity and acceleration vectors, and its size"
  []
  {:position [(/ (q/width)  2) 25]
   :velocity [0.0 0.0]
   :acceleration [0.0 0.15]
   :size 50})

(defn update-state
  "Accelerates the ball downwards until it hits the bottom of the screen"
  [ball]
  (let [{:keys [position velocity acceleration size]} ball]
    (if (> (nth position 1)
           (- (q/height) (/ size 2)))
      ball
      (assoc ball :velocity (map + velocity acceleration)
                  :position (map + position velocity)))))

(defn draw-state
  "Draws the ball"
  [ball]
  ; color the background
  (q/background 51)
  ; disable the stroke
  (q/no-stroke)
  ; set the stroke to violet
  (q/fill 120 0 255)
  ; draw the ball
  (let [{[x y] :position size :size} ball]
    (q/ellipse x y size size)))
