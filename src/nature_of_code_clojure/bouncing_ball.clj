(ns nature-of-code-clojure.bouncing-ball
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup
  "Setup the simulation and return the initial state"
  []
  ; set frame rate to 60 frames per second.
  (q/frame-rate 60)
  ; the initial state
  {:position  [(/ (q/width)  2) (/ (q/height) 2)]
   :velocity  [2.0 -2.5]})

(defn bounce-ball
  "Return a bounced ball position and velocity"
  [ball]
  (let [{[x y]   :position
         [dx dy] :velocity} ball
         new-dx (if (or (< x 0) (> x (q/width)))
                  (* dx -1)
                  dx)
         new-dy (if (or (< y 0) (> y (q/height)))
                  (* dy -1)
                  dy)]
    {:position (map + [x y] [new-dx new-dy])
     :velocity [new-dx new-dy]}))


(defn update-state
  "Returns the next state given the current state"
  [state]
  ; return a bounced ball
  (bounce-ball state))

(defn draw-state
  "Given the current state, draw it"
  [state]
  ; color the background
  (q/background 51)
  ; disable the stroke
  (q/no-stroke)
  ; set the stroke and fill to azure
  (q/fill 0 127 255)
  ; draw the walker itself
  (let [{[x y] :position} state]
    (q/ellipse x y 50 50)))

(defn run-simulation
  "Runs the simulation"
  []
  (q/defsketch simulation
    :title "Bouncing Ball"
    :size [500 500]
    ; setup function called only once, during sketch initialization.
    :setup setup
    ; update-state is called on each iteration before draw-state.
    :update update-state
    :draw draw-state
    :features [:keep-on-top]
    ; This sketch uses functional-mode middleware.
    ; Check quil wiki for more info about middlewares and particularly
    ; fun-mode.
    :middleware [m/fun-mode]))
