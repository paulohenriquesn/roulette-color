(ns modules.roullete.roullete-service
  (:require [constants.roullete]
            [nano-id.core :refer [nano-id]]))

(defn roll
  "Roll roullete and give two colors random"
  []
  (let [colors (repeatedly 2 #(rand-nth constants.roullete/colors))]
    (if (= (first colors) (second colors))
      (recur)
      {:colors colors :id (nano-id)})))