#!/bin/bash

dir="$(pwd)"
cd ..

sudo docker-compose stop
sudo docker-compose rm --force

cd $dir