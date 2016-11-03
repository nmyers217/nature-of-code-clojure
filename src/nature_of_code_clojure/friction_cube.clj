(ns nature-of-code-clojure.friction-cube
  (:require [quil.core :as q]))

(defn create-surface
  "Returns the data for a surface"
  [friction-amt color rect]
  {:friction-amt friction-amt
   :color color
   :rect rect})

(defn create-world
  "Return a map containing the surfaces (grass, dirt, and ice) for the game world"
  []
  (map #(apply create-surface %)
       [[0.05 [0   255 0]   [0   500 500 10]]
        [0.10 [122 100 80]  [500 500 250 10]]
        [0.01 [100 160 255] [750 500 250 10]]]))

(defn create-player
  "Return a map containing the player data"
  []
  {:position [20 475]
   :velocity [0.0 0.0]
   :acceleration [0.0 0.0]
   :color [255 0 0]
   :size 25
   :mass 2
   :velocity-cap 5})

(defn create-forces
  "Return a map of force functions"
  []
  {:gravity (fn [mass] [0.0 0.01])})

(defn setup
  "Returns the initial state"
  []
  {:world  (create-world)
   :player (create-player)
   :forces (create-forces)})

(defn update-state
  "Updates the world to the next state"
  [state]
  state)

(defn on-key-down
  "Updates the state when a key is pressed"
  [state event]
  state)

(defn on-key-up
  "Updates the state when a key is released"
  [state]
  state)

(defn draw-surface
  "Draws a surface"
  [{:keys [color rect]}]
  (apply q/fill color)
  (apply q/rect rect))

(defn draw-world
  "Draws the world"
  [world]
  (doseq [surface world] (draw-surface surface)))

(defn draw-player
  "Draws a player"
  [{:keys [color size position]}]
  (apply q/fill color)
  (q/rect (first position) (second position) size size))

(defn draw-state
  "Draws the current state"
  [state]
  (q/background 51)
  (let [{:keys [world player]} state]
    (draw-world world)
    (draw-player player)))
