start-rabbitmq:
	docker run -d -p 5672:5672 --hostname my-rabbit --name clojure-rabbit rabbitmq:3
