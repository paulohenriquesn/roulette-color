(ns tests.roullete
  (:require [clojure.test :refer :all]
            [modules.roullete.roullete-service :as roullete-service]))

;; Check if first bet is the same as second bet
(is (not= (first (get (roullete-service/roll) :id))
          (first (get (roullete-service/roll) :id))))


