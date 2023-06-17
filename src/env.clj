(ns env
  (:require [environ.core :as environ]))

(def jwt-secret (if (nil? (environ/env "JWT_SECRET")) "dev"))

(def database-envs
  (if (nil? (environ/env "DB_NAME"))
    {:DB_NAME     "dev"
     :DB_PORT     5432
     :DB_USER     "dev"
     :DB_PASSWORD "dev"}
    {:DB_NAME     (environ/env "DB_NAME")
     :DB_PORT     (environ/env "DB_PORT")
     :DB_USER     (environ/env "DB_USER")
     :DB_PASSWORD (environ/env "DB_PASSWORD")}))
