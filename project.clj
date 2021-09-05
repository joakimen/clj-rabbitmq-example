(defproject clj-queue-consumer-example "0.1.0-SNAPSHOT"
  :description "RabbitMQ-consumer in Clojure"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.novemberain/langohr "5.2.0"]]
  :main ^:skip-aot clj-queue-consumer-example.core
  :profiles {:uberjar {:aot :all
                       :uberjar-name "app.jar"}})
