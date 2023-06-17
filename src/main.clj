(ns main
  (:require [modules.user.user-controller :as user-controller]
            [ring.adapter.jetty :as jetty]
            [compojure.core :refer :all]
            [ring.middleware.json :as middleware]))

(defroutes app-routes (user-controller/fn-user-routes))

(def app
  (-> app-routes
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)))

(defn -main []
  (jetty/run-jetty app {:port 8080}))
