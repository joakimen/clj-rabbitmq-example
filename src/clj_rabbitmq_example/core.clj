(ns clj-rabbitmq-example.core
  (:gen-class)
  (:require [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]))

(def ^{:const true}
  default-exchange-name "")

(def qname "joakimen.clj-queue-example.hello-queue")

(defn message-handler
  [ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s, delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8") delivery-tag content-type type)))


(defn -main
  [& args]

  (with-open [conn  (rmq/connect)
              ch    (lch/open conn)]
    (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))

    ;; create a queue
    (lq/declare ch qname {:exclusive false :auto-delete false})

    ;; produce a message to it
    (println "[producer] Publishing message to" qname)
    (lb/publish ch default-exchange-name qname "Hello!" {:content-type "text/plain" :type "greetings.hi"})
    (println "[producer] Message was published to" qname)

    ;; subscribe to queue
    (lc/subscribe ch qname message-handler {:auto-ack true})

    (println "[main] Disconnecting...")))


(comment
  (-main)

  (defn connect
    "RabbitMQ config"
    []
    (let [conn (rmq/connect)]
      {:conn conn
       :ch (lch/open conn)
       :qname "joakimen.clj-queue-example.hello-queue"}))

  (defn disconnect [ch conn]
    (rmq/close ch)
    (rmq/close conn))

  ;; delete queue
  (let [{:keys [conn ch qname]} (connect)]
    (lq/delete ch qname)
    (disconnect ch conn))

   ;; create queue
  (let [{:keys [conn ch qname]} (connect)]
    (lq/declare ch qname)
    (disconnect ch conn))

  ;; produce to queue
  (let [{:keys [conn ch qname]} (connect)]
    (lb/publish ch default-exchange-name qname "Hello!" {:content-type "text/plain" :type "greetings.hi"})
    (disconnect ch conn))

  ;; consume from queue
  (let [{:keys [conn ch qname]} (connect)]
    (lc/subscribe ch qname message-handler {:auto-ack true})
    (disconnect ch conn)))
