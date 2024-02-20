# Spring Boot REST-service

### Create docker Image

mvn package

docker image build -t alxinsh/docker-java-test-app:9.0.0 .  
docker push alxinsh/docker-java-test-app:9.0.0

### Install DB Postgres from Helm
helm install my-release oci://registry-1.docker.io/bitnamicharts/postgresql

### Deploy service into K8s
kubectl apply -f ./k8s/

### Cleanup
kubectl delete -f ./k8s/
helm delete my-release
kubectl delete pvc -l release=my-release
