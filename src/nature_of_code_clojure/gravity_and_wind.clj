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
  [width height forces ball]
  (let [{[x y] :position [dx dy] :velocity mass :mass} ball
        ;; calculate the new components of velocity
        bounce-dx (if (or (< x 0) (> x width))
                    (* dx -1)
                    dx)
        bounce-dy (if (or (< y 0) (> y height))
                    (* dy -1)
                    dy)
        ;; calculate the acceleration vector for this ball using the forces
        acceleration (reduce #(map + %1 %2)
                             (map #((second %) mass) forces))
        ;; add acceleration and velocity and constrain the result
        new-vel (map (comp #(q/constrain % -8 8) +)
                     [bounce-dx bounce-dy]
                     acceleration)]
    (assoc ball :position (map + [x y] new-vel) :velocity new-vel)))

(defn setup
  "Return a vector of 20 ball maps and a map of force functions"
  []
  {:balls (random-balls (q/width) (q/height) 20)
   :forces {:gravity (fn [mass] [0.0 0.01])
            :wind    (fn [mass] (map #(/ % mass) [0.03 0.0]))}})

(defn update-state
  "Accelerates and bounces all the balls"
  [state]
  (let [{:keys [balls forces]} state]
    (assoc state :balls
           (map #(bounce-ball (q/width) (q/height) forces %) balls))))

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
