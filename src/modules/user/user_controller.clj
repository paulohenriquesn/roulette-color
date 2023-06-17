(ns modules.user.user-controller
  (:require [compojure.core :refer :all]
            [cheshire.core :as json]
            [modules.user.user-service :as service]))

(defroutes user-routes
           (POST "/register" request
                 (let [email (-> request :body :email)
                       password (-> request :body :password)]
                   (service/create email password))
                 {:status  200
                  :headers {"Content-Type" "application/json"}
                  :body    (json/encode {:message "OK"})}))

(defn fn-user-routes [] user-routes)
