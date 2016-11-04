(ns nature-of-code-clojure.friction-cube
  (:require [quil.core :as q]))

;; Setup functions
(defn create-surface
  "Returns the data for a surface"
  [friction-amt color rect]
  {:friction-amt friction-amt
   :color color
   :rect rect})

(def surface-types
  "A function to generate each surface type at a given rect"
  {:grass #(create-surface 0.05 [0 255 0] %)
   :dirt  #(create-surface 0.10 [122 100 80] %)
   :ice   #(create-surface 0.01 [100 160 255] %)})

(defn create-world
  "Return a vector of surfaces for the game world"
  []
  (map (fn [{:keys [type rect]}]
         ((type surface-types) rect))
       [{:type :grass :rect [0 500 500 10]}
        {:type :dirt  :rect [500 500 250 10]}
        {:type :ice   :rect [750 500 250 10]}]))

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
  {:gravity (fn [mass] [0 0])})

(defn setup
  "Returns the initial state"
  []
  {:world  (create-world)
   :player (create-player)
   :forces (create-forces)})


;; Update functions
(defn move-player
  "Adds the player's velocity to their position"
  [forces player]
  (let [new-acc (reduce #(map + %1 ((second %2) (:mass player))) [0 0] forces)
        new-vel (map + (:velocity player) new-acc)
        new-pos (map + (:position player) new-vel)]
    (assoc player :acceleration new-acc :velocity new-vel :position new-pos)))

(defn update-state
  "Updates the world to the next state"
  [state]
  (update state :player #(move-player (:forces state) %)))

;; TODO: Make this properly and smoothly accelerate the player
;; Need a key map in the state to track the pressed keys
;; The actual updates to anything besides the keymap should take place in update-state
(defn on-key-down
  "Updates the state when a key is pressed"
  [state event]
  (case (:key event)
    (:a :left)  (assoc-in state [:forces :thrust]
                          (fn [mass] (map #(/ % mass) [-0.5 0.0])))
    (:d :right) (assoc-in state [:forces :thrust]
                          (fn [mass] (map #(/ % mass) [0.5 0.0])))
    state))

;; TODO: Make this properly and smoothly accelerate the player
;; Need a key map in the state to track the pressed keys
;; The actual updates to anything besides the keymap should take place in update-state
(defn on-key-up
  "Updates the state when a key is released"
  [state]
  (if (contains? #{:left :right :a :d} (q/key-as-keyword))
    (-> state
        (assoc-in [:forces :thrust] (fn [mass] [0 0]))
        (assoc-in [:player :velocity] [0 0]))
    state))


;; Drawing functions
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
