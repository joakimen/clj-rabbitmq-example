start-rabbitmq:
	docker run -d \
		-p 5672:5672 \
		-p 15672:15672 \
		--hostname my-rabbit \
		--name clojure-rabbit \
		rabbitmq:3-management
