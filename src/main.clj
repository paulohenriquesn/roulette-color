(ns main
  (:require [modules.user.user-controller :as user-controller]
            [ring.adapter.jetty :as jetty]
            [compojure.core :refer :all]
            [ring.middleware.json :as middleware]
            [clojure.java.io :as io]))

(defroutes app-routes
           (GET "/" [] (io/resource "views/index.html"))
           (GET "/login" [] (io/resource "views/login.html"))
           (GET "/register" [] (io/resource "views/register.html"))
           (GET "/scripts/main.js" [] (io/resource "views/scripts/main.js"))
           (user-controller/fn-user-routes))

(def app
  (-> app-routes
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)))

(defn -main []
  (jetty/run-jetty app {:port 8080}))
