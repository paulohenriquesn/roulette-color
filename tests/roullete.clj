(ns tests.roullete
  (:require [clojure.test :refer :all]
            [modules.roullete.roullete-service :as roullete-service]
            [modules.user.user-service :as user-service]))
(roullete-service/roll)

;; Check if first bet is the same as second bet
(is (not= (first (get (roullete-service/roll) :id))
          (first (get (roullete-service/roll) :id))))


;; Check if first color is same as the second and throw if is
(is (= true
       (try
         (do
           (user-service/bet 1 ["green" "green"] 1) false)
         (catch Exception e true))))