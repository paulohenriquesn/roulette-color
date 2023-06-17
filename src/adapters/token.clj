(ns adapters.token (:require [buddy.sign.jwt :as jwt]
                             [env]))

(defn generate-token [user-id]
  (jwt/sign {:user-id user-id} env/jwt-secret))