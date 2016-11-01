(ns nature-of-code-clojure.gravity-and-wind
  (:require [quil.core :as q]))

(defn random-ball
  "Creates a random ball within a given width and height"
  [width height]
  {:position [(rand width) (rand height)]
   :velocity [0.0 0.0]
   :mass (+ (rand 4) 1)})

(defn random-balls
  "Creates n amount of random balls within the given width and height"
  [width height n]
  (into [] (repeatedly n #(random-ball width height))))

(defn bounce-ball
  "Return a bounced ball position and velocity"
  [width height acceleration ball]
  (let [{[x y] :position [dx dy] :velocity} ball
        ; reverse the x component of velocity when a boundary is hit
        bounce-dx (if (or (< x 0) (> x width))
                    (* dx -1)
                    dx)
        ; reverse the y component of velocity when a boundary is hit
        bounce-dy (if (or (< y 0) (> y height))
                    (* dy -1)
                    dy)
        ; add acceleration and velocity and constrain the result
        new-vel (map #(q/constrain % -8 8)
                     (map + [bounce-dx bounce-dy] acceleration))]
    (assoc ball :position (map + [x y] new-vel)
           :velocity new-vel)))

(defn setup
  "Return a vector of 20 ball maps and a vector of the forces"
  []
  {:balls (random-balls (q/width) (q/height) 20)
   :forces [[0 0.01] [0.01 0]]})

(defn update-state
  "Accelerates and bounces all the balls"
  [state]
  (let [{:keys [balls forces]} state
        acceleration (reduce #(map + %1 %2) [0 0] forces)]
    (assoc state :balls (map
                         #(bounce-ball (q/width) (q/height) acceleration %)
                         balls))))

(defn draw-state
  "Given the current state, draw it"
  [{:keys [balls]}]
  (q/background 51)
  (q/stroke 0)
  (q/fill 0 127 255)
  (loop [[b & bs] balls]
    (if (not-empty b)
      (let [{[x y] :position mass :mass} b]
        (q/ellipse x y (* mass 16) (* mass 16))
        (recur bs)))))
