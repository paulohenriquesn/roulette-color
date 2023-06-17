(ns adapters.crypto
  (:require [crypto.password.bcrypt :as crypto]))

(defn encrypt-password [password]
  (crypto/encrypt password))

(defn verify-password [hash password]
  (crypto/check hash password))

