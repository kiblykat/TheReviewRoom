#! /bin/bash

cd ..

REGISTRY='ap-southeast-1.amazonaws.com'
IMAGE='the-review-room:latest'

docker build -t $REGISTRY/$IMAGE .

aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin $REGISTRY
docker push $REGISTRY/$IMAGE
