# Automatically Testing an End-to-End Java Application

## Repo Structure

### angular front-end
HTML files that you must to put in your application server.
_service.js_ file on _js_ folder is pointing to a local API. If you need to change the server and port, changhe this on the file:
`http://localhost:4567/api/v1/person/:id`

### database
The backend app is running on a MySQL server, so you need to create the database before any execution throught _database.sql_ file.

### javaone-android-app
The mobile front-end built on Android Studio.
The app is pointing to the local service, but here the IP address is not localhost, it is _10.0.3.2_ and you can change it on `APIEndpointsInterface.java` class.
Just notice that this IP adress works locally only on Genymotion emulator. For Android AVD you must set the IP _10.0.2.2_ or your computer IP if you run on a real device.

### javaone-backend
The backend app. It's a REST service created with Java Spark.
This is a CRUD app with MySQL database

### javaone-test-project
The test project that contains all the test scripts for API, Web and Mobile Tests

## Repositiry for two presentations at JavaOne 2017
* Java Test Automation for REST, Web, and Mobile [CON6070]
* Trust Your Pipeline: Automatically Testing an End-to-End Java Application [CON6121]
