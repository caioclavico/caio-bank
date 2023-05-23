(ns front.views
  (:require
   [re-frame.core :as re-frame]
   [front.events :as events]
   [front.subs :as subs]
   [front.routes :as routes]
   [front.pages.login.views :refer [page-login]]
   [front.pages.cadastro.views :refer [page-cadastro]]
   [react-router-dom :refer [BrowserRouter Route Switch useHistory]]
   [reagent.core :as r]))

(defn page-not-found []
  [:h1 "404 - Page not found"]) ;;? como adicionar uma imagem?

(defn ->rota [path page]
  [:> Route {:path path :exact true :component (r/reactify-component (fn [] page))}])

(defn main-panel []
  [:> BrowserRouter
   [:> Switch
    (->rota "/login"    (page-login))
    (->rota "/cadastro" (page-cadastro))
    (->rota "*"         (page-not-found))]])
