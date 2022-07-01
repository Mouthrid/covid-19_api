#!/bin/sh

cd ..
docker build -t harbor.mouthird.com/project/covid19-backend:latest -f docker/Dockerfile .
docker push harbor.mouthird.com/project/covid19-backend:latest
