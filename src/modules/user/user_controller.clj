(ns modules.user.user-controller
  (:require [compojure.core :refer :all]
            [cheshire.core :as json]
            [modules.user.user-service :as service]
            [middlewares.is-auth]))

(defroutes user-routes
           (POST "/register" request
             (let [email (-> request :body :email)
                   password (-> request :body :password)]
               (service/create email password))
             {:status  200
              :headers {"Content-Type" "application/json"}
              :body    (json/encode {:message "OK"})})

           (POST "/login" request
             (let [email (-> request :body :email)
                   password (-> request :body :password)
                   token (service/login email password)]
               {:status  200
                :headers {"Content-Type" "application/json"}
                :body    (json/encode {:token token})}))

           (POST "/bet" request
             (let [coins (-> request :body :coins)
                   colors (-> request :body :colors)
                   user-id (-> (middlewares.is-auth/is-auth request) :user-id)
                   result (service/bet user-id colors coins)]
               {:status  200
                :headers {"Content-Type" "application/json"}
                :body    (json/encode result)}))

           (GET "/balance" request
             (let [
                   user-id (-> (middlewares.is-auth/is-auth request) :user-id)
                   balance (service/balance user-id)]
               {:status  200
                :headers {"Content-Type" "application/json"}
                :body    (json/encode balance)})))

(defn fn-user-routes [] user-routes)