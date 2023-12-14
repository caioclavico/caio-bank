(ns front.pages.cadastro.events
  (:require
   [ajax.core :as ajax]
   [front.util :as util]
   [day8.re-frame.http-fx]
   [re-frame.core :as re-frame]
   [reagent.cookies :as cookies]
   [front.db :as db]))

(re-frame/reg-event-db
 ::insert-data
 (fn [db [_ caminho valor]]
   (-> db
       (update :cadastro-form dissoc :erros)
       (assoc-in [:cadastro-form caminho] valor))))

(re-frame/reg-event-db
 ::cadastro-success
 (fn [db [_ _response]]
   (if (cookies/get-raw :messages)
     (assoc-in db [:cadastro-form :erros] ["Usuário inválido."])
     (.reload js/location))))

(re-frame/reg-event-db
 ::cadastro-failure
 (fn [db [_ _response]]
   (assoc-in db [:cadastro-form :erros] ["Não foi possível realizar o cadastro."])))

(re-frame/reg-event-fx
 ::try-cadastro
 (fn [{:keys [db]} [_]]
   (let [{:keys [nome cpf senha]} (:cadastro-form db)
         _ (cookies/remove! :messages)
         token (cookies/get-raw :token)
         form-data (doto
                    (js/FormData.)
                     (.append "nome" nome)
                     (.append "user_cpf" cpf)
                     (.append "senha" senha)
                     (.append "token" token))]
     (.log js/console form-data)
     {:db db
      :http-xhrio {:method :post
                   :uri "/efetua_cadastro"
                   :headers (util/->headers)
                   :body form-data
                   :timeout 6000
                   :response-format (ajax/raw-response-format)
                   :on-success [::cadastro-success]
                   :on-failure [::cadastro-failure]}})))

(re-frame/reg-event-db
 ::try-cadastro-mock
 (fn [db]
   (let [form (:cadastro-form db)
         cpf (keyword (:cpf form))]
     (prn @db/mock-db)
     (if-not (get-in @db/mock-db [:usuarios cpf])
       (if (:cpf form)
         (swap! db/mock-db assoc-in [:usuarios cpf] form)
         (assoc-in db [:cadastro-form :erros] ["Usuário inválido."]))
       (do (prn "usuario já existe")
           (assoc-in db [:cadastro-form :erros] ["Usuário já existe."]))))))
