docker run --name tomcat-server-app \
-p 8081:8080 \
-v /Users/mateusgobo/dockerdata/tomcat/webapps:/usr/local/tomcat/webapps \
-d tomcat:8.5.71-jdk8-openjdk-buster