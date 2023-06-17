(ns modules.user.user-service
  (:require [databases.pg]
            [clojure.java.jdbc :as j]
            [ports.crypto]
            [ports.token]
            [modules.roullete.roullete-service]))

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

(defn find-one-by-id [id]
  (let [rows (j/query databases.pg/db ["SELECT * FROM users WHERE id = ?" id])]
    (if (= nil (first rows))
      (throw (Exception. "Ops user not found!"))
      (first rows))))

(defn login
  [email password]
  (let [user (find-one email)]
    (if (= true (ports.crypto/verify-password (-> user :password) password))
      (str (ports.token/generate-token (-> user :id)))
      (throw (Exception. "Password invalid!")))))


(defn win [user-id quantity]
  (let [user (find-one-by-id user-id)]
    (j/execute! databases.pg/db ["UPDATE users SET coin = ? WHERE id = ?" (+ (-> user :coin) quantity) user-id]))
  (println "User" user-id "wins" quantity "coins"))

(defn balance [user-id]
  (println user-id)
  (let [user (find-one-by-id user-id)
        balance (-> user :coin)]
    balance))

(defn lose [user-id quantity]
  (let [user (find-one-by-id user-id)]
    (j/execute! databases.pg/db ["UPDATE users SET coin = ? WHERE id = ?" (- (-> user :coin) quantity) user-id]))
  (println "User" user-id "loses" quantity "coins"))

(defn bet [user-id colors coins]
  (println user-id colors coins)
  (let [user (find-one-by-id user-id)]
    (println user)
    (if (< (-> user :coin) coins)
      (throw (Exception. "Coins insufficient to bet"))
      (let [result (modules.roullete.roullete-service/roll)]
        (if (or (= (first result) (first colors))
                (= (first result) (second colors))
                (= (second result) (first colors))
                (= (second result) (second colors)))
          (win user-id (* coins 2))
          (lose user-id coins))
        result))))