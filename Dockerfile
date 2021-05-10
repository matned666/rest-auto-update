FROM adoptopenjdk/openjdk11:ubi
ADD target/jsonplaceholder-1000-Miles.jar .
CMD java -jar jsonplaceholder-1000-Miles.jar