# Spring Boot REST-service

### Create docker Image

mvn package

docker image build -t alxinsh/docker-java-test-app:9.0.0 .  
docker push alxinsh/docker-java-test-app:9.0.0

### Install DB Postgres from Helm
helm install my-release oci://registry-1.docker.io/bitnamicharts/postgresql

### Enable Minikube tunnel
minikube tunnel

### Deploy service into K8s
kubectl apply -f ./k8s/

### Cleanup
kubectl delete -f ./k8s/
helm delete my-release
kubectl delete pvc -l release=my-release


curl http://localhost:8000/user | json_pp

curl -X POST --header 'Content-Type: application/json' \
-d '{ "username": "JohnDoeUser", "firstName": "John", "lastName": "Doe", "email": "some@mail.com", "phone": "+79158456" }'\
http://localhost:8000/user

curl -X DELETE http://localhost:8000/user/5

curl -X PUT --header 'Content-Type: application/json' \
-d '{ "username": "JohnDoeUserUpdated", "firstName": "JohnUpdated", "lastName": "DoeUpdated", "email": "some@mail.com.updated", "phone": "+7915845600" }'\
http://localhost:8000/user/1