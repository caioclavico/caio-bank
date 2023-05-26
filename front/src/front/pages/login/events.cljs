(ns front.pages.login.events
  (:require
   [ajax.core :as ajax]
   [front.util :as util]
   [day8.re-frame.http-fx]
   [re-frame.core :as re-frame]
   [reagent.cookies :as cookies]))

(re-frame/reg-event-db
 ::insert-data
 (fn [db [_ caminho valor]]
   (-> db
       (update :login-form dissoc :erros)
       (assoc-in [:login-form caminho] valor))))

(re-frame/reg-event-db
 ::login-success
 (fn [db [_ response]]
   (if (cookies/get-raw :messages)
     (assoc-in db [:login-form :erros] ["Usuário inválido."])
     (do
       (.log js/console (str response))
       (.reload js/location)))))

(re-frame/reg-event-db
 ::login-failure
 (fn [db [_ {:keys [status status-text]}]]
   (.log js/console (str "Não foi possível realizar o login: " status " " status-text))
   (assoc-in db [:login-form :erros] ["Não foi possível realizar o login."])))

(re-frame/reg-event-fx
 ::try-login
 (fn [{:keys [db]} [_]]
   (let [{:keys [cpf senha]} (:login-form db)
         _ (cookies/remove! :messages)
         token (cookies/get-raw :token)
         form-data (doto
                    (js/FormData.)
                     (.append "user_cpf" cpf)
                     (.append "senha" senha)
                     (.append "token" token))]
     (.log js/console form-data)
     {:db db
      :http-xhrio {:method :post
                   :uri "/efetua_login"
                   :headers (util/->headers)
                   :body form-data
                   :timeout 6000
                   :response-format (ajax/raw-response-format)
                   :on-success [::login-success]
                   :on-failure [::login-failure]}})))
