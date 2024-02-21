# Spring Boot REST-service
The Spring Boot service expose CRUD to the user.  
The database credentials are specified in ENV.  
ENV are specified in secret.yaml

### Create docker Image

mvn package

docker image build -t alxinsh/docker-java-test-app:9.0.14 .  
docker push alxinsh/docker-java-test-app:9.0.14

### Install DB Postgres from Helm
helm install my-release oci://registry-1.docker.io/bitnamicharts/postgresql

### Enable Minikube tunnel
minikube tunnel

Add external tunnel IP to file: /etc/hosts
192.168.49.2 arch.homework

### Deploy service into K8s
kubectl apply -f ./k8s/

### Cleanup
kubectl delete -f ./k8s/  
helm delete my-release  
kubectl delete pvc -l release=my-release

### Test requests
Get all users:  
curl http://arch.homework/user | json_pp  

Get user by ID:  
curl http://arch.homework/user/1 | json_pp  

Add user:  
curl -X POST --header 'Content-Type: application/json' \  
-d '{ "username": "JohnDoeUser", "firstName": "John", "lastName": "Doe", "email": "some@mail.com", "phone": "+79158456" }' \  
http://arch.homework/user

Update user:  
curl -X PUT --header 'Content-Type: application/json' \  
-d '{ "username": "JohnDoeUserUpdated", "firstName": "JohnUpdated", "lastName": "DoeUpdated", "email": "some@mail.com.updated", "phone": "+7915845600" }' \  
http://arch.homework/user/1

Delete user by ID:  
curl -X DELETE http://arch.homework/user/5
