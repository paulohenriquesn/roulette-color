(ns modules.roulette.roulette_service
  (:require [constants.roulette]
            [nano-id.core :refer [nano-id]]))

(defn roll
  "Roll roulette and give two colors random"
  []
  (let [colors (repeatedly 2 #(rand-nth constants.roulette/colors))]
    (if (= (first colors) (second colors))
      (recur)
      {:colors colors :id (nano-id)})))