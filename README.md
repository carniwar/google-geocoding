# google-geocoding

Google Geocoding API proxy with Maven, Spring Boot, Camel and JUnit tests.

This project wraps the call to Google API (https://developers.google.com/maps/documentation/geocoding/intro) receiving and parameter address and returning its geocodes (latitude and longitude).

The endpoint to call this API will be available at http://localhost:8081/google/api/geocode, via GET method, and expects query parameter address. For example:
* http://localhost:8081/google/api/geocode?address=mcdonalds

For more than one request to Google API, a developer key will be need on project package. Use argument -Dgoogle.api.key to pass the key to Maven process. Ex: -Dgoogle.api.key=QWERTY123456. To create a new key, access https://console.developers.google.com/apis/credentials?project=_, create a new project, create a new API key and assign it to Google Geocoding API.

To compile run "mvn package -Dgoogle.api.key=...". The package will be available at target/google-geocoding-1.0.0-SNAPSHOT.jar.

To execute without package run "mvn spring-boot:run -Dgoogle.api.key=...". After package, run "java -jar target/google-geocoding-1.0.0-SNAPSHOT.jar".
