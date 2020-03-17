# Acceptance Test Project

This project contains all the resources needed to execute the Acceptance Tests of the Four-Eyes Service.

## Overview

This project is made of different parts:

- A Maven project whith the Acceptance Tests definition (using Robotframework), configured to execute the tests via the `robotframework-maven-plugin`.
- Docker-compose configuration that is used to setup the ION Platform for the test environment.
- Scripts to simplify and orchestrate the execution of the tests.

## Files and Folders

This section describes the relevant content of the repository, folders and files, organized according to their purpose.

### For the setup of the ION Platform

- `config/` and `solution/`: folders that contain the standard resources used by the ION Installer to setup the platform.
- `docker-compose.yml`: definition of the containers that will be used to simulate the hosts involved in the solution deploy.\
Typically:
  - `platform_host`: the host where the DM and the components are deployed
  - `controller_host`: the host from which the ION Installer deploys the components on the `platform_host`
  - `maven_test_runner`: a simple container with Maven installed
  - _db servers_ : when needed, additional containers can host the database

### For the Acceptance Tests definition

- `pom.xml`: the POM of the Maven project, with the configuration of the `robotframework-maven-plugin`.
- `settings.xml`: the Maven configuration used when tests are executed
- `src/test/resources/`: the Acceptance Tests, keywords and tests suites, written in Robotframework
- `src/main/`: code for Java keywords definition

### For the execution

- `scripts/`: scripts, for linux (`.sh`) and win (`.ps1`), to run and coordinate the execution of the tests.\
Relevant scripts are:
  - `start_test_env`: prepare the ION Platform using the ION Installer
  - `stop_test_env`: stop the ION Platform and remove the related containers
  - `<test_env>`: use Maven to run the tests from a specific test environment agains a running platform.\
  Example: `default.sh`

    ```bash
    #!/bin/bash
    mvn clean verify -Dstep=Environments.Default -s settings.xml
    ```

  - `run_<test_env>`: start a container with Maven and run, from it, the script to execute the tests of given test environment.\
  Example: `run_default.sh`

    ```bash
    #!/bin/bash
    cd ..
    sudo docker-compose run maven_test_runner /bin/bash -c /src/scripts/default.sh
    cd -
    ```

### Runtime

- `wrk/<xxx>_logs/`: these folders contain all the logs (of the platform components, the daemon and the installer) generated during the setup and the test execution
- `wrk/ion_repo/`: the local repo where the ION Installer downloads the components release files, to be unzipped and deployed
- `wrk/m2_repo/`: the .m2 repository of the `maven_test_runner` container
- `target/robotframework/`: the output files, logs and report, of the Robotframework test execution.

## Sample usage of the scripts

All the following scripts are executed from the `script/` folder.

Initialize the ION Platform

```bash
$./start_test_env.sh
```

...when the script execution is completed, the ION Platform is up and running and can be also reached via SysAdmin.

Run the tests of the _Default_ test environment, if Maven is available

```bash
$./default.sh
```

...the `robotframework-maven-plugin` is executed and the test results will be reported in `target/robotframework/`.

Run the tests of the _Default_ test environment from the `maven_test_runner` container

```bash
$./run_default.sh
```

...again the results are in in `target/robotframework/`.

Stop and clear the platform:

```bash
$./stop_test_env.sh
```