(ns front.components.components
  (:require
   ["@mui/material/styles" :refer [createTheme ThemeProvider]]
   ["@mui/material" :refer [AppBar Container Typography TextField Button Toolbar]]
   [re-frame.core :as re-frame]
   [front.util :as util]
   [reagent.core :as r]))

(def tema (createTheme))

(defn tipo->variant [tipo]
  (case tipo
    "principal" "contained"
    "secundario" "outlined"
    "default"))

(defn Botao
  [{:keys [tipo label evento id disabled? tamanho]}]
  [:> ThemeProvider {:theme tema}
   [:> Button {:id id
               :disabled disabled?
               :onClick #(re-frame/dispatch evento)
               :variant (tipo->variant tipo)
               :size tamanho} label]])

#_{:clj-kondo/ignore [:unresolved-symbol]}
(defn Input
  [{:keys [id label tipo variante required evento subscricao mascara]}]
  (r/with-let [valor-subscricao (re-frame/subscribe subscricao)]
    [:> TextField {:id id
                   :label label
                   :variant variante
                   :fullWidth true
                   :required required
                   :type tipo
                   :value (or @valor-subscricao "")
                   ;;:onChange #(re-frame/dispatch [evento id (util/valor-parser (-> % .-target .-value) mascara)])
                   :onChange #(re-frame/dispatch (conj evento (util/valor-parser (-> % .-target .-value) mascara)))}]))

(defn Header
  []
  [:> AppBar {:position "static"}
   [:> Container {:maxWidth "xl"}
    [:> Toolbar {:disableGutters true
                 :sx {:justifyContent {:xs "center" :sm "flex-start"}}}
     [:> Typography {:variant "h6"
                     :noWrap true
                     :component "a"
                     :href "/login"
                     :sx {:mr 2
                          :fontFamily "monospace"
                          :fontWeight 700
                          :letterSpacing ".3rem"
                          :color "inherit"
                          :textDecoration "none"}}
      "CaioBank"]]]])
