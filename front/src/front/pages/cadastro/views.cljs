(ns front.pages.cadastro.views
  (:require
   [front.pages.cadastro.events :as events]
   [front.pages.cadastro.subs :as subs]
   [front.components.components :refer [tema Botao Input Header]]
   ["@mui/material/styles" :refer [ThemeProvider]]
   ["@mui/material" :refer [CssBaseline Container Typography Avatar Link Stack]]
   ["@mui/icons-material" :refer [Assignment]]))

(defn page-cadastro []
  [:> ThemeProvider {:theme tema}
   [:> CssBaseline]
   [Header]
   [:> Container {:component "main" :maxWidth "xs"}
    [:> Stack {:sx {:marginTop 8
                    :alignItems "center"
                    :gap 2
                    :border "2px solid gray"
                    :borderRadius "15px"
                    :padding "30px"}
               :component "form"}
     [:> Avatar {:sx {:m 1 :bgcolor "secondary.main"}}
      [:> Assignment]]
     [:> Typography {:component "h1" :variant "h5"} "Cadastro"]
     [Input {:id "nome"
             :label "Nome"
             :variante "outlined"
             :required true
             :evento [::events/insert-data :nome]
             :subscricao [::subs/form-data :nome]}]
     [Input {:id "cpf"
             :label "CPF"
             :variante "outlined"
             :required true
             :evento [::events/insert-data :cpf]
             :subscricao [::subs/form-data :cpf]}]
     [Input {:id "password"
             :label "Senha"
             :variante "outlined"
             :tipo "password"
             :required true
             :evento [::events/insert-data :senha]
             :subscricao [::subs/form-data :senha]}]
     [:> Stack {:direction "row" :spacing 4 :alignItems "center"}
      [Botao {:label "Cadastrar"
              :tipo "principal"
              :tamanho "large"
              :evento [::events/try-cadastro]}]
      [:> Link {:href "/login" :variant "body2"} "Voltar"]]]]])
