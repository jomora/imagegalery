# imagegallery
The Imagegallery is a project which purpose is to be presented and reviewed at a job interview taking place in Darmstadt, Germany, at the end of February 2016. 

Upon completion the following features will be available:
- Upload fotos via a web form
- View fotos previously uploaded in an image galery
- Query images by keywords (contained in the image description)
- Register, and log in into the gallery to only allow for private access

Todos:
- Asynchronous upload: currently not working due to problems with CORS configuration on the server side
- ...
- 

Technology stack:
Java, Spring Boot, Hibernate, PostgreSQL, Thymeleaf, jQuery 

How to run the project:
- first setup a database and then add the according tables using the available src/main/resources/database-setup.sql script
- configure the database access in the application.properties file (remark: currently there is only the application.properties.template available. copy this file and remove ".template" to get a working properties file)

- open a terminal in the corresponding folder (imagegallery/.)
- run the following command: mvn package spring-boot:run
- the imagegallery will be available under localhost:8080/gallery
- enjoy :)
