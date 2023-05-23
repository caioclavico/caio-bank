(ns front.components.components
  (:require
   [re-frame.core :as rf]
   ["@mui/material/styles" :refer [createTheme ThemeProvider]]
   ["@mui/material" :refer [AppBar Container Typography Avatar Link Menu TextField Button Toolbar Tooltip IconButton]]))

(def tema (createTheme))

(defn tipo->variant [tipo]
  (case tipo
    "principal" "contained"
    "secundario" "outlined"
    "default"))

(defn Botao
  [{:keys [tipo label evento id disabled? tamanho]}]
  [:> ThemeProvider {:theme tema}
   [:> Button {:id id :disabled disabled? :on-click #(rf/dispatch evento) :variant (tipo->variant tipo) :size tamanho} label]])

(defn Input
  [{:keys [id label tipo variante required]}]
  [:> TextField {:id id :label label :variant variante :fullWidth true :required required :type tipo}])

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
