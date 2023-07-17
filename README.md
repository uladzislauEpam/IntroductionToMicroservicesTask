Startup:\
gradle build\
docker-compose up\
Eureka:\
http://localhost:8761\
ResourceApplication:\
http://localhost:8080\
SongApplication:\
http://localhost:8090\
\
Postman ex:\
POST http://localhost:8080/api/resource
body: form-data[mp3_file]
DELETE http://localhost:8080/api/resource/1

