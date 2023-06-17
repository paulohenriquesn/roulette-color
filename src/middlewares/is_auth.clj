(ns middlewares.is-auth
  (:require [buddy.sign.jwt :as jwt]
            [env]))

(defn is-auth [request]
  (let [headers (-> request :headers) token (get headers "authorization")]
    (println "Validating" token)
     (try (jwt/unsign token env/jwt-secret)
          (catch Exception e (throw (Exception. "Invalid token"))))))