hSenid Customer Care Portal
===

Introduction
---
This is a web-based system to manage subscribers of different different applications.


System Requirements
---
- JDK 1.7
- apache-tomcat-7
- Mysql 5.5 or higher
- Maven 3


Configuration Details
---

System configurations settings can be found at `system_configuration.properties` file.

### Database connection settings

* **jdbc.url      :** database connection url. Follows this pattern:

        jdbc:mysql://<host>:<port>/<db_name>?createDatabaseIfNotExist\=true

* **jdbc.username :** database username
* **jdbc.password :** database password



### Resource Bundle 'messages'

In order to customize the displaying messages in the portal please update the below files.

* `messages_en.properties` - English Version

### Logs

Update the log folder in the `logback.xml` file

   * **log_path  :** Path to log directory


Build & Deploy
---

* Modify configuration files as required.

        system_configuration.properties
        logback.xml

* Build the project using following maven command

         mvn clean install -DskipTests

* Create the database _customercare_ (you may change the name)

* Copy following files to tomcat webapps folder

        customer-care.war

* Start tomcat server and deploy the application

* Now it should create the necessary tables

* Then source the following sql file:

         data/initial_data.sql

