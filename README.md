# clj-rabbitmq-example

Example project demonstrating how to produce to and consume from a queue in RabbitMQ.

![build-badge](https://github.com/joakimen/clj-rabbitmq-example/actions/workflows/clojure.yml/badge.svg)

## Usage

### 1. Start RabbitMQ-server

```sh
$ make start-rabbitmq
```

### 2. Run application to produce and consume a message

```sh
$ lein run
[main] Connected. Channel id: 1
[producer] Publishing message to joakimen.clj-queue-example.hello-queue
[producer] Message was published to joakimen.clj-queue-example.hello-queue
[main] Disconnecting...
[consumer] Received a message: Hello!, delivery tag: 1, content type: text/plain, type: greetings.hi
```

## Managing RabbitMQ

The makefile-target `start-rabbitmq` will start a RabbitMQ-server in docker. The command forwards two ports; 
* `5672` - The port Clojure will connect to during message exchange
* `15672` - The port providing access to the management ui

To access the RabbitMQ Management UI, navigate to http://localhost:15672 in your browser.
* user: `guest`
* pass: `guest`
