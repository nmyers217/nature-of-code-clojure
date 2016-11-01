(ns nature-of-code-clojure.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:require [nature-of-code-clojure.example :as example]
            [nature-of-code-clojure.random-walker :as random-walker]
            [nature-of-code-clojure.bouncing-ball :as bouncing-ball]
            [nature-of-code-clojure.falling-ball :as falling-ball]
            [nature-of-code-clojure.accel-towards-mouse :as atm]
            [nature-of-code-clojure.gravity-and-wind :as gaw]))

;;; TODO: One day make this run a gui where you can select a simulation
(println
  (str
    "Run a simulation from the REPL!\n"
    "=> (use 'nature-of-code-clojure.core)\n"
    "=> (run-[simulation name])"))

(defmacro setup-sketch
  "Constructs and runs a simulation given its meta data"
  [sketch-name title size setup-fn update-fn draw-fn nav?]
  `(q/defsketch ~(symbol sketch-name)
    :title  ~title
    :size   ~size
    ; setup function called only once, during sketch initialization.
    :setup  ~setup-fn
    ; update-state is called on each iteration before draw-state.
    :update ~update-fn
    :draw   ~draw-fn
    ; the window will always be on top even when unfocused
    ; this makes live development easier
    :features [:keep-on-top]
    ; These sketches use functional-mode middleware.
    ; Check quil wiki for more info about middlewares and particularly
    ; fun-mode.
    :middleware (if ~nav?
                    [m/fun-mode m/pause-on-error m/navigation-2d]
                    [m/fun-mode m/pause-on-error])))

(defn run-example
  "Runs the example simulation"
  []
  (setup-sketch
    "example-simulation" "You spin my circle right round"
    [500 500]
    example/setup example/update-state example/draw-state
    false))

(defn run-random-walker
  "Runs the random walker simulation"
  []
  (setup-sketch
    "random-walker-simulation" "Random Walker"
    [500 500]
    random-walker/setup random-walker/update-state random-walker/draw-state
    true))

(defn run-bouncing-ball
  "Runs the bouncing ball simulation"
  []
  (setup-sketch
    "bouncing-ball-simulation" "Bouncing Ball"
    [500 500]
    bouncing-ball/setup bouncing-ball/update-state bouncing-ball/draw-state
    false))

(defn run-falling-ball
  "Runs the falling ball simulation"
  []
  (setup-sketch
    "falling-ball-simulation" "Falling Ball"
    [500 500]
    falling-ball/setup falling-ball/update-state falling-ball/draw-state
    false))

(defn run-accel-towards-mouse
  "Runs the accel towards mouse simulation"
  []
  (setup-sketch
    "accel-towards-mouse-simulation" "Accelerate Towards Mouse"
    [1000 1000]
    atm/setup atm/update-state atm/draw-state
    false))

(defn run-gravity-and-wind
  "Runs the bouncing ball simulation"
  []
  (setup-sketch
   "gravity-and-wind-simulation" "Gravity and Wind"
   [1000 1000]
   gaw/setup gaw/update-state gaw/draw-state
   false))
