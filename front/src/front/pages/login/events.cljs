(ns front.pages.login.events
  (:require
   [re-frame.core :as re-frame]
   [reagent.cookies :as cookies]))

(defn handler [response]
  (.log js/console (str response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))

(re-frame/reg-event-db
 ::insert-data
 (fn [db [_ caminho valor]]
   (-> db
       (update :login-form dissoc :erros)
       (assoc-in [:login-form caminho] valor))))

(re-frame/reg-event-fx
 ::try-login
 (fn [{:keys [db]} [_]]
   (let [{:keys [cpf senha]} (:login-form db)
         _ (cookies/remove! :messages)
         token (cookies/get-raw :token)
         form-data (doto (js/FormData.)
                     (.append "user_cpf" cpf)
                     (.append "senha" senha)
                     (.append "token" token))]
     {:db db
      :http-xhrio {:method :post
                   :uri "/efetua_login/"
                   :headers () ;; fazer a funcao
                   :body form-data
                   :timeout 6000
                   :handler handler
                   :error-handler error-handler
                   :on-success [::login-success]
                   :on-failure [::login-failure]}})))

(re-frame/reg-event-db
 ::login-success
 (fn [db [_ response]]
   (if (cookies/get-raw :messages)
     (assoc-in db [:login-form :erros] ["Usuário inválido."])
     (.reload js/location))))

(re-frame/reg-event-db
 ::login-failure
 (fn [db [_ response]]
   (assoc-in db [:login-form :erros] ["Não foi possível realizar o login."])))
