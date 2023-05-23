(ns front.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::url-atual
 :<- [::temp]
 (fn [temp [_]]
   (:url-atual temp)))
