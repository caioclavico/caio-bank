(ns front.pages.login.views
  (:require
   [front.components.components :refer [tema Botao Input Header]]
   ["@mui/material/styles" :refer [ThemeProvider]]
   ["@mui/material" :refer [CssBaseline Container Stack Typography Avatar Link]]
   ["@mui/icons-material" :refer [LockOutlined]]))

(defn page-login []
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
      [:> LockOutlined]]
     [:> Typography {:component "h1" :variant "h5"} "Login"]
     [Input {:id "cpf"
             :label "CPF"
             :variante "outlined"
             :required true}]
     [Input {:id "password"
             :label "Senha"
             :variante "outlined"
             :tipo "password"
             :required true}]
     [Botao {:label "Entrar"
             :tipo "principal"
             :tamanho "large"}]
     [:> Link {:href "/cadastro" :variant "body2"} "Não possui conta? Cadastre-se"]]]])
