(ns front.pages.login.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::form-data
 (fn [db [_ caminho]]
   (get-in db [:login-form caminho])))

(re-frame/reg-sub
 ::erros
 (fn [db [_]]
   (get-in db [:login-form :erros])))

(re-frame/reg-sub
 ::sucesso
 (fn [db [_]]
   (get-in db [:login-form :sucesso])))
