#!/bin/bash

cd ..
sudo docker-compose run maven_test_runner /bin/bash -c /src/scripts/default.sh
cd -
