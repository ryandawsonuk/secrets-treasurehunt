# Treasure Hunt

## Aims

To explore kubernetes secrets with a treasure hunt game in minikube.

## Pre-requisites

Docker (https://docs.docker.com/install/) and minikube (https://kubernetes.io/docs/tasks/tools/install-minikube/) installed.

## How to Run In Minikube

Start minikube:
 
`minikube start --memory 4000 --cpus 3`

Build image for minikube - from this directory run

`eval $(minikube docker-env)` <br/>
`docker build . -t treasurehunt`

Deploy with
 
`kubectl create -f ./treasurehunt`

First access from: 

`minikube service treasurehunt-entrypoint`

And play by going to e.g. `http://localhost:30080/treasure?x=1&y=1`

Delete with
 
`kubectl delete -f ./treasurehunt`

And stop with `minikube stop`