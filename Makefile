
build:
	mvn package;

start:
	docker run -p 8080:8080 --name line-counter-api-rest guzzz101/line-counter-api-rest:1.0.0

stop:
	docker stop line-counter-api-rest
	docker rm line-counter-api-rest

clear: 
	docker rmi guzzz101/line-counter-api-rest:1.0.0