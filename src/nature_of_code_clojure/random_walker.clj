(ns nature-of-code-clojure.random-walker
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup
  "Setup the simulation and return the initial state"
   []
   ; set frame rate to 60 frames per second.
   (q/frame-rate 60)
   ; the state should be a list of vectors
   (list [(/ (q/width)  2)
          (/ (q/height) 2)]))

(defn update-state
  "Returns the next state given the current state"
  [state]
  (let [pos      (first state)
        velocity [(q/random -2 2) (q/random -2 2)]
        next-pos (map + pos velocity)]
    ; add the next position to the beginning of the list
    (cons next-pos state)))

(defn draw-state
  "Given the current state, draw it"
  [state]
  ; color the background
  (q/background 51)
  ; set the fill and stroke colors to violet
  (q/fill   127 0 255)
  (q/stroke 127 0 255)
  ; draw the walker itself
  (loop [points state]
    (when (=  (count (take 2 points)) 2)
      (apply q/line (take 2 points))
      (recur (drop 1 points)))))

(defn run-simulation
  "Runs the simulation"
  []
  (q/defsketch simulation
    :title "Random Walker"
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
