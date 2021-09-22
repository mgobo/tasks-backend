docker run --name jenkins \
--privileged \
--network jenkins \
--network-alias jenkins \
-v /Users/mateusgobo/dockerdata/jenkins_home:/var/jenkins_home \
-v /Users/mateusgobo/apache-tomcat-8.5.50:/var/jenkins_home/workspace/apache \
-p 9100:8080 \
-p 9101:50000 \
-d jenkins/jenkins:alpine

#host.docker.internal
docker run --name jenkins \
--privileged \
-v /Users/mateusgobo/dockerdata/jenkins_home:/var/jenkins_home \
-p 9100:9001 \
-p 9101:50000 \
-d jenkins:2.313-alpine-jdk8