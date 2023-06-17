(ns modules.user.user-service
  (:require [databases.pg]
            [clojure.java.jdbc :as j]
            [ports.crypto]))

(defn create
  "Creates a user with the given email and password"
  [email password]
  (println "Creating user" email "with password" password)
  (let [hash-password (ports.crypto/encrypt-password password)]
    (j/insert! databases.pg/db :users {:email email :password hash-password})
    (println "User" email "registered with hash password" hash-password))
  nil)
