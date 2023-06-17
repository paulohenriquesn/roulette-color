(ns tests.user
  (:require [clojure.test :refer :all]
            [modules.user.user-service :as user-service]))

;; Check if first color is same as the second and throw if is
(is (= true
       (try
         (do
           (user-service/bet 1 ["green" "green"] 1) false)
         (catch Exception e true))))