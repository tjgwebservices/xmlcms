# tjgxmlcms
A content management tool that separates content from presentation with xml

tjgxmlcms creates a sqlite database.  Development is on the way to configure tjgxmlcms for xml data and other data sources.
## running the application
To run the app:

*mvn -Dspring-boot.run.mainClass=com.tjgwebservices.ConferenceServer.ConferenceServerApplication spring-boot:run*

If the tests fail, run the command again.  The application creates a test database and test data.  The test database may not be created before the tests attempt to access the test data.

## accessing the application

Once started, the application will be available at localhost:8080.  
