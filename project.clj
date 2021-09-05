(defproject clj-rabbitmq-example "0.1.0-SNAPSHOT"
  :description "Example of production and consumption to RabbitMQ in Clojure"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.novemberain/langohr "5.2.0"]
                 [com.fzakaria/slf4j-timbre "0.3.21"]]
  :main ^:skip-aot clj-rabbitmq-example.core
  :profiles {:uberjar {:aot :all
                       :uberjar-name "app.jar"}})
