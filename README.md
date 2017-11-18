# A simple RESTFul webservice that integrates with the PushBullet API (https://docs.pushbullet.com)

### Install and run from source
* Clone repository
* Compile and run with Spring boot for Maven
```
mvn spring-boot:run -X -e -Drun.jvmArguments="-Xdebug"
```

### Run as a jar
* This requires the jar file to have been built (for example by Maven mvn package)
* Run jar directly
```
java -jar target/push-bullet-integration-${version}.jar
java -jar target/push-bullet-integration-0.1.0.jar
```

### API resources

#### GET /users
Returns the list of users registered with the API
* Headers. None required
* Body/Form data. None required
* Returns. A list of User objects (See below for details of the User object)

#### POST /users
Registers a user with the API. A user is uniquely identified with their username and can only be registered once.
* Headers. Content-Type: application/json
* Body/Form data. A User object (See below for details of the User object)
* Returns. The details of the user in the use store. If the user already existed in the user store, then the existing user details are returned.

#### User object
Field name | Description | Type | Mandatory
------------ | ------------- | ------------ | -------------
username | User unique identifier | String | Yes
accessToken | PushBullet user token | String | Yes
numOfNotificationsPushed | Number of push notifications sent | String | No. Returned in responses
creationTime | Registration timestamp | Datetime | No. Returned in responses

### Customise application
Customisable settings like the application context can be controlled by passing the correct Spring options.
Example content of a properties file:
```
server.context-path=/
server.tomcat.max-http-post-size=3145728
```
These can be passed to Spring Boot when running from source like so
```
mvn spring-boot:run -X -e -Drun.jvmArguments="-Xdebug -Dspring.config.location=applications.properties"
```
If running the jar, the java options can be passed in the e.g. _JAVA_OPTIONS

### Potential improvements
* Persistent data store for list of users
* Strict Json schema validations
