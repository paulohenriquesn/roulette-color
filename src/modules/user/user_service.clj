(ns modules.user.user-service
  (:require [databases.pg]
            [clojure.java.jdbc :as j]
            [ports.crypto]
            [ports.token]))

(defn create
  "Creates a user with the given email and password"
  [email password]
  (println "Creating user" email "with password" password)
  (let [hash-password (ports.crypto/encrypt-password password)]
    (j/insert! databases.pg/db :users {:email email :password hash-password})
    (println "User" email "registered with hash password" hash-password))
  nil)

(defn find-one [email]
  (let [rows (j/query databases.pg/db ["SELECT * FROM users WHERE email = ?" email])]
    (if (= nil (first rows))
      (throw (Exception. "Ops user not found!"))
      (first rows))))

(defn login
  [email password]
  (let [user (find-one email)]
    (if (= true (ports.crypto/verify-password (-> user :password) password))
      (str (ports.token/generate-token (-> user :id)))
      (throw (Exception. "Password invalid!")))))
