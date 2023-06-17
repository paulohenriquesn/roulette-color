(ns tests.roulette
  (:require [clojure.test :refer :all]
            [modules.roulette.roulette_service :as roulette-service]))

;; Check if first bet is the same as second bet
(is (not= (first (get (roulette-service/roll) :id))
          (first (get (roulette-service/roll) :id))))


