(ns front.pages.cadastro.views
  (:require
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
             :required true}]
     [Input {:id "cpf"
             :label "CPF"
             :variante "outlined"
             :required true}]
     [Input {:id "password"
             :label "Senha"
             :variante "outlined"
             :tipo "password"
             :required true}]
     [:> Stack {:direction "row" :spacing 4 :alignItems "center"}
      [Botao {:label "Cadastrar"
              :tipo "principal"
              :tamanho "large"}]
      [:> Link {:href "/login" :variant "body2"} "Voltar"]]]]])
