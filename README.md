# cmapi_test
This is a Spring Boot MVC mini-app that includes a RESTFul API for the following features:
1) Uploading a file with metadata.
2) Persisting the metadata in an H2 database (in-memory) using the default schema (testdb). 
   The files are stored on the file system.
3) Retrieving/Searching for the content by A) All. B) Content ID, and C) Content Name. 
   The later two can be used by a search interface, response is in the form of JSON (List or a specific item).
4) Retrieving the actual file by ID. 
5) A Scheduler that polls the new items that were added in the last hour and sends an email.
   Email settings are in the application.properties. If a gmail account is used then it should be configured to
   allow less secure apps to login. (https://myaccount.google.com/lesssecureapps).
   
 To build and run using Gradlew: gradlew bootRun.
