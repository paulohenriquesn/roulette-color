(ns ports.token (:require adapters.token))

(defn generate-token [user-id] (adapters.token/generate-token user-id))