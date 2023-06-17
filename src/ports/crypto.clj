(ns ports.crypto
  (:require [adapters.crypto :as crypto]))

(defn encrypt-password [password]
  (crypto/encrypt-password password))
