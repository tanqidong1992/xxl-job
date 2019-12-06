FROM 192.168.0.140:8084/hnvmns/java:8u192b12_server-jre
MAINTAINER QidongTan
 
ADD xxl-job-admin-*.jar /app.jar

ENV JAVA_OPTS="-Dspring.profiles.active=docker"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]