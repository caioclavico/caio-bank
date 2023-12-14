(ns front.routes
  (:require
   [re-frame.core :as re-frame]
   [front.events :as events]))

(defmulti panels identity)
(defmethod panels :default [] [:div "No panel found for this route."])

(def routes
  (atom
   ["/" {"login" :login
         "about" :about
         "cadastro" :cadastro
         "users" {"" :users-index
                  ["/" :id] :user-view}}]))

(defn dispatch
  [route]
  (let [panel (keyword (str "page-" (name (:handler route))))]
    #_(re-frame/dispatch [::events/set-active-panel panel])
    (re-frame/dispatch [::events/set-route {:route route :panel panel}])))
