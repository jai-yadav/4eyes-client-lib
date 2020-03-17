#!/bin/bash

dir="$(pwd)"
cd ..

sudo rm -rf ./wrk/platform_logs/*
sudo rm -rf ./wrk/installer_logs/*
sudo rm -rf ./wrk/dm_logs/*
sudo rm -rf ./wrk/ITAS/*
sudo rm -rf ./wrk/ion_repo/*

sudo docker-compose up -d --no-recreate --scale controller_host=0 --scale maven_test_runner=0
sleep 5
sudo docker-compose run controller_host /bin/bash -c /src/scripts/install_from_local_repo.sh

cd $dir