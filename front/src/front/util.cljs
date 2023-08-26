(ns front.util
  (:require
   [clojure.string :as str]
   [reagent.cookies :as cookies]))

(defn rota-existe? [rota]
  (#{"/login"} rota))

(defn ->headers []
  (let [token-acesso (cookies/get-raw :token_acesso)]
    {"Authorization" (str "Bearer " token-acesso)}))

(defn valor-parser [v tipo]
  (when (seq v)
    (case tipo
      (:cpf :cnpf) (str/replace v #"[\. \- / _ ( )]" "")
      v)))

(defn ->path [img]
  (str "/assets/img/" img))
