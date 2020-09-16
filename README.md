**Project based in API REST - JAVA**

*Deployed in:*  https://line-counter-api-rest.herokuapp.com/

- API that returns the total number of lines and the total number of bytes of all the files of a given public Github repository, grouped by file extension.

---

# LINE COUNTER API REST 

This API is very simple, and consists in three calls.

1. **Download**: Save a git repository into a temporary directory. *This method always clean the temporary directory before every download.*
2. **Analyse**: Get info about the git repository already downloaded. This method returns 404 if don't find anything inside temporary directory. 
3. **Clean**: Additional method that deletes the temporary directory.

---

## Swagger

This API uses Swagger as a documentation tool.

* Usually we would have to access: "swagger-ui/index.html" to see Swagger's documentation. However, in this project the root is redirecting to this endpoint.

---

## Endpoints

If you rather use Postman to test the endpoints, they are listed below:

1. **_"/api/download"_** - *(Returns 400 if the url inserted is not valid)*
2. **_"/api/analyse"_** - *(Returns 404 if there is no archive previous downloaded)*
3. **_"/api/clean"_** - *(Returns 200 and different messages)*

_The download method is a POST method, the others are GET methods. The first one you should send some url. Example: { "url": "https://github.com/guzzz/studioJ3D_V2.git" }_

---

## Tests

There are two tests being tested in this project. Both check the *analyse endpoint*.

1. The first one check if the analyse endpoint return OK after get some data.
2. The second one check if the analyse endpoint return NOT_FOUND after not finding anything in the temporary directory.

---

## Run Locally

Some Makefile commands:

* **_make build_**: Create the project's image.
* **_make start_**: Create the project's container and run the project.
* **_make stop_**: Stop and then remove the project's container.
* **_make clear_**: Remove the project's image.

1. In the first time (and just in the first one) you will have to use the command _make build_ to create the project's image, and then _make start_ to run it locally. Next time, you will just have to use _make start_ and _make stop_ .
2. This project runs in: http://localhost:8080/

---

## Docker Hub

This project's image is also in a docker hub repository. To run it locally using this method, you should use the commands below:

```
docker pull guzzz101/line-counter-api-rest:1.0.0
docker run -p 8080:8080 --name line-counter-api-rest guzzz101/line-counter-api-rest:1.0.0
```

---

## Requirements

* **JAVA**: 11.0.8
* **MAVEN**: 3.6.3
* **DOCKER**: 19.03.12

*This project does not persist data.*
