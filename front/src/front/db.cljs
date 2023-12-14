(ns front.db)

(def default-db
  {:login-form {:cpf nil
                :senha nil}
   :usuarios {:admin {:senha "admin"
                      :nome "admin"
                      :cpf "admin"}}})

(def mock-db (atom {:usuarios
                    {:admin
                     {:senha "admin"
                      :nome "admin"
                      :cpf "admin"}}}))
