(ns modules.roulette.roulette_service
  (:require [constants.roulette]
            [nano-id.core :refer [nano-id]]))

(defn roll
  "Roll roulette and give two colors random"
  []
  (let [color (rand-nth constants.roulette/colors)]
      {:color color :id (nano-id)}))