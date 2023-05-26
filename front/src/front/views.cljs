(ns front.views
  (:require
   [front.util :as util]
   [front.pages.login.views :refer [page-login]]
   [front.pages.cadastro.views :refer [page-cadastro]]
   [react-router-dom :refer [BrowserRouter Route Switch]]
   [reagent.core :as r]))

(defn page-not-found []
  [:img {:alt "404 - Page not found"
         :src (util/->path "404-not-found.jpg")}]) ;;? como adicionar uma imagem?

(defn ->rota [path page]
  [:> Route {:path path :exact true :component (r/reactify-component (fn [] page))}])

(defn main-panel []
  [:> BrowserRouter
   [:> Switch
    (->rota "/login"    (page-login))
    (->rota "/cadastro" (page-cadastro))
    (->rota "*"         (page-not-found))]])
