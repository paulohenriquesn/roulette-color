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
                :body    (json/encode {:token token})})
             )
           (POST "/bet" request
             (let [headers (-> request :headers) token (get headers "authorization")]
               (middlewares.is-auth/is-auth request)
               {:status  200
                :headers {"Content-Type" "application/json"}
                :body    (json/encode {:token headers})})
             ))

(defn fn-user-routes [] user-routes)