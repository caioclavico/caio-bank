(ns front.pages.login.views
  (:require
   [front.pages.login.events :as events]
   [front.pages.login.subs :as subs]
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
     [Input {:id "id_user_cpf"
             :label "CPF"
             :mascara :cpf
             :variante "outlined"
             :required true
             :evento [::events/insert-data :cpf]
             :subscricao [::subs/form-data :cpf]}]
     [Input {:id "id_senha"
             :label "Senha"
             :variante "outlined"
             :tipo "password"
             :required true
             :evento [::events/insert-data :senha]
             :subscricao [::subs/form-data :senha]}]
     [Botao {:label "Entrar"
             :tipo "principal"
             :tamanho "large"
             :evento [::events/try-login-mock]}]
     [:> Link {:href "/cadastro" :variant "body2"} "Não possui conta? Cadastre-se"]]]])
