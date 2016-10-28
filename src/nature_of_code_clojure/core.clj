(ns nature-of-code-clojure.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;(q/defsketch simulation
;  :title "Current Simulation"
;  :size [500 500]
;  ; setup function called only once, during sketch initialization.
;  :setup sketch/setup
;  ; update-state is called on each iteration before draw-state.
;  :update sketch/update-state
;  :draw sketch/draw-state
;  :features [:keep-on-top]
;  ; This sketch uses functional-mode middleware.
;  ; Check quil wiki for more info about middlewares and particularly
;  ; fun-mode.
;  :middleware [m/fun-mode])

;;; TODO: One day make this run a gui where you can select a simulation
(println "Run a simulation from the REPL!")
