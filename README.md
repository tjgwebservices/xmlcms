# tjgxmlcms
A content management tool that separates content from presentation with xml

#Summary

TJGXMLCMS is an integration of tools to support content management.  TJGXMLCMS has interfaces in a number of programming languages to allow for easy integration into existing platforms.  TJGXMLCMS separates content from the presentation layer with XML, permitting the facilitation of the deployment of systems agnostic of the business domain.  TJGXMLCMS has applications in publishing, education, research, media, and science.  Internal TJGXMLCMS applications can integrate secure XML techniques for all industries.

TJGXMLCMS is a suite of tools that can be integrated and customized for existing systems.  TJGXMLCMS can also serve as a standalone content management tool.  TJGXMLCMS is designed with a number of emerging software development practices.  TJGXMLCMS is developed with practices such as test driven development, behavior driven development, shift left programming, and value stream modelling.  These practices provide rapid developments of features for the content management tool.

#Installation and Deployment

tjgxmlcms creates a sqlite database.  Development is on the way to configure tjgxmlcms for xml data and other data sources.  To run the application locally, install maven and openjdk 11 or higher.  Clone the source code and run maven to install dependencies and run the application.

## running the application
To run the app:

*mvn clean install -Dspring-boot.run.mainClass=com.tjgwebservices.ConferenceServer.ConferenceServerApplication spring-boot:run*

If the tests fail, run the command again.  The application creates a test database and test data.  The test database may not be created before the tests attempt to access the test data. After running the tests the second time all the test data will be populated.

## accessing the application

Once started, the application will be available at localhost:8080. 

More information about the TJGXMLCMS project can be found [here](https://tjgwebservices.com/tjgxmlcms/).
