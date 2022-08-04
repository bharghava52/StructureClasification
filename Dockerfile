FROM openjdk:11
EXPOSE 9099
ADD target/StructureClasification-0.0.1-SNAPSHOT.jar StructureClasification.jar
ENTRYPOINT ["java","-jar","/StructureClasification.jar"];