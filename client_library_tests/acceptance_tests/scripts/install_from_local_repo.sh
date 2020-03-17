#!/bin/bash

cd /installer/autoconfx

./ion.sh download -sf /src/solution/solution_local.yml

./ion.sh deploy

./ion.sh initDB

./ion.sh start -dm

./ion.sh configure

./ion.sh start -ss

