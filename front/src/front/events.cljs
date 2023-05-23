(ns front.events
  (:require
   [re-frame.core :as re-frame]
   [front.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::update-name
 (fn [db [_ val]]
   (assoc db :name val)))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(re-frame/reg-event-fx
 ::set-route
 (fn-traced [{:keys [db]} [_ route]]
            {:db (assoc db :route route)}))
