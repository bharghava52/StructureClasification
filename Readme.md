# Structure Classification

### in this project you will be able to compare the tags of two files mainly html and xml file tags. you can compare n number of files at the same time

## UI Build
```
cd view
npm i
ng build --prod
```

## JAR Build
```
mvn clean install
```

## Docker Build
```
docker build --no-cache -t structureclasification:latest .
```

## Kubernetes Build
```
docker tag structureclasification:latest bharghava52/structureclasification:latest
docker push bharghava52/structureclasification:latest
kubectl apply -f structureclasification.yml
```

## Kubernetes ingress
```
minikube addons enable ingress
kubectl apply -f structureclasification-ingress.yml
```

## set ipmapping
```
sudo nano /etc/hosts
```
add the mapping to structureclasification.com