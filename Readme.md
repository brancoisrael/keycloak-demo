#Build
docker build -f Dockerfile -t java-keycloak:latest .

#Local run
docker run -p 8081:8081 -d java-keycloak:latest

#Docker Tag
docker tag java-keycloak:latest brancoisrael/java-keycloak:latest

#DockerHub push
docker push brancoisrael/java-keycloak:latest





