echo "build project"
mvn clean install
echo "docker build images"
docker build -f Dockerfile -t appevent .
echo "down container"
docker-compose -f Docker-compose.yml rm -f -s appevent
echo "start project"
docker-compose -f Docker-compose.yml up -d