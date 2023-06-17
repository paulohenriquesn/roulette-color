(ns databases.pg (:require [env]))

(def db {:dbtype "postgresql"
         :port (get env/database-envs :DB_PORT)
         :user (get env/database-envs :DB_USER)
         :dbname (get env/database-envs :DB_NAME)
         :password (get env/database-envs :DB_PASSWORD)})