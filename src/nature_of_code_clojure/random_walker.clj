(ns nature-of-code-clojure.random-walker
  (:require [quil.core :as q]))

(defn setup
  "Setup the simulation and return the initial state"
   []
   ; the walker is a list of vectors
   {:walker (list [(/ (q/width)  2)
                   (/ (q/height) 2)])
    :navigation-2d {}})

(defn update-state
  "Returns the next state given the current state"
  [state]
  (let [walker   (:walker state)
        pos      (first walker)
        velocity [(q/random -2 2) (q/random -2 2)]
        next-pos (map + pos velocity)]
    ; add the next position to the beginning of the list
    (assoc state :walker (cons next-pos walker))))

(defn draw-state
  "Given the current state, draw it"
  [state]
  ; color the background
  (q/background 51)
  ; set the fill and stroke colors to violet
  (q/fill   127 0 255)
  (q/stroke 127 0 255)
  ; draw the walker itself
  (loop [points (:walker state)]
    (when (=  (count (take 2 points)) 2)
      (apply q/line (take 2 points))
      (recur (drop 1 points)))))
