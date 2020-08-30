# Springboot with Redis Sentinel using Docker Compose

```shell
git clone https://github.com/akashsolanki/springboot-redis.git
```
Run below shell script which sets some env variable and triggers docker-compose up
```shell
> ./start.sh`
```
```shell
mvn spring-boot:run
```
```shell
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"item":"1","name":"Sausage"}' \
  http://localhost:8080
```
```shell
curl --request GET http://localhost:8080
```

Borrowed from - \
https://github.com/mustafaileri/redis-cluster-with-sentinel \
https://github.com/xavierchow/docker-redis-sentinel
