# Treasure Hunt

## Run with Docker

Build with:

`docker build . -t treasurehunt`

Start with:

`docker run -it -p 8080:8080 -e TREASURE_LOCATION_X=14 -e TREASURE_LOCATION_Y=17 treasurehunt`

Play by going to `http://localhost:8080/hunt?x=<X>&y=<Y>` where X and Y are integers.

See the treasure map and start again by going to `http://localhost:8080/reset`

## In Minikube

Start minikube:
 
`minikube start --memory 4000 --cpus 3`

Build image for minikube - from this directory run

`eval $(minikube docker-env)` <br/>
`docker build . -t treasurehunt`

Deploy with
 
`kubectl create -f treasurehunt.yml`

First access from: 

`minikube service treasure-hunt-entrypoint`

And play by going to `http://localhost:30080/hunt?x=<X>&y=<Y>` where X and Y are integers.

Delete with
 
`kubectl delete -f treasurehunt.yml`

And stop with `minikube stop`