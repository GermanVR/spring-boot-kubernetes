# spring-boot-kubernetes
Proyecto Skeleton con Kubernetes para leer ConfigMap y Secrets

#Construir artefacto "jar"
mvn clean package


#Run Docker build and Push

docker build -t darqko/spring-boot-kubernetes:v1.0.0 .

docker push darqko/spring-boot-kubernetes:v1.0.0
