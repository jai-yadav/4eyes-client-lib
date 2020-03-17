#!/bin/groovy

def projectDir = 'client_library/'
def mockServerPath = 'client_library_tests/mocks/mock_4eyes_server/'
def mockDOpath = 'client_library_tests/mocks/mock_data_owner/'
def e2eTestProject = 'client_library_tests/acceptance_tests/'


pipeline {

    agent {
        label "maven_docker_slave"
    }

    options {
        gitLabConnection('ion_gitlab')
        disableConcurrentBuilds()
    }

    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All')
    }

    stages {
        stage("Build Project") {
            steps {
                mavenInstall(projectDir)
            }
        }
        stage("Build Dependant Projects") {
            steps {
                parallel(
                        mock_4eyes_server: {
                                        sh """
                                        cd ${mockServerPath}
                                        mvn clean install
                                        """
                        },
                        mock_data_owner: {
                                        sh """
                                        cd ${mockDOpath}
                                        mvn clean install
                                        """
                        }
                )
            }
        }
        stage("Prepare E2E Tests Execution") {
            steps {
                script {
                    if (['develop', 'master'].contains(env.BRANCH_NAME)) {
                        echo 'Pushing on ' + env.BRANCH_NAME + ' -> Deploy on Nexus'
                        parallel(
                                client_lib: {
                                            sh """
                                            cd ${projectDir}
                                            mvn clean deploy -DaltSnapshotDeploymentRepository=nexus::default::http://angstrom02:8081/content/repositories/snapshots
                                            """
                                },
                                mock_4eyes_server: {
                                            sh """
                                            cd ${mockServerPath}
                                            mvn clean deploy -DaltSnapshotDeploymentRepository=nexus::default::http://angstrom02:8081/content/repositories/snapshots
                                            """
                                },
                                mock_data_owner: {
                                            sh """
                                            cd ${mockDOpath}
                                            mvn clean deploy -DaltSnapshotDeploymentRepository=nexus::default::http://angstrom02:8081/content/repositories/snapshots
                                            """
                                }
                        )
                    } else {
                        local_repo = 'file:' + env.WORKSPACE + '/client_library_tests/acceptance_tests/wrk/m2_repo'
                        echo 'Pushing on the ' + env.BRANCH_NAME + ' feature branch -> Prepare tests'
                        echo 'Deploying to local directory: ' + local_repo
                        parallel(
                                mock_4eyes_server: {
                                    sh """
                                            cd ${mockServerPath}
                                            mvn clean deploy -DaltSnapshotDeploymentRepository=nexus::default::${local_repo}
                                            """
                                },
                                mock_data_owner: {
                                    sh """
                                            cd ${mockDOpath}
                                            mvn clean deploy -DaltSnapshotDeploymentRepository=nexus::default::${local_repo}
                                            """
                                }
                        )
                    }
                }
            }
        }
        stage("Execute E2E Tests") {
            steps {
                script {
                    if (['develop', 'master'].contains(env.BRANCH_NAME)) {
                        executeTest(e2eTestProject, 'run_default.sh', 'start_test_env.sh')
                    } else {
                        executeTest(e2eTestProject, 'run_default.sh', 'start_test_env_local.sh')
                    }
                }
            }
        }
    }
}

def mavenInstall(folder) {
    sh """
        cd ${folder}
        mvn clean install
       """
}

def executeTest(e2eFolder, testScript, envStartScript) {
    // all the paths are relative to e2eFolder
    dir(e2eFolder) {
        script {
            try {
                sh """
                #!/bin/bash

                cd scripts
                sudo chmod -R 777 *.sh
                set +e
                
                ./${envStartScript}
                sleep 10s
                ./${testScript}
                RETCODE=\$?
                
                ./stop_test_env.sh
                cd -
                echo RETCODE: \$RETCODE
                exit \$RETCODE
                """
            } finally {
                archiveArtifacts artifacts: 'target/robotframework/*'
                publishTestResults()
            }
        }
    }
}

def publishTestResults() {
    step([
            $class           : 'hudson.plugins.robot.RobotPublisher',
            outputPath       : './target/robotframework/',
            passThreshold    : 100,
            unstableThreshold: 0,
            otherFiles       : '',
            reportFileName   : 'report.html',
            logFileName      : 'log.html',
            outputFileName   : 'output.xml'
    ])
}