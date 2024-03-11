pipeline {
    agent any

    environment {
        SERVICE_NAME = 'airconnect-manager'
        JENKINS_DOCKER_HOST = '127.0.0.1:2375'
        STACK_SERVICE_NAME = "${SERVICE_NAME}"
        DEV_MANAGER_NODE = '10.10.100.1:2377'
        TEST_MANAGER_NODE = '10.10.100.2:2377'
        DOCKER_HOST = '127.0.0.1:2376'
        DOCKER_REGISTRY = '127.0.0.1:8899'
        DOCKER_REPOSITORY = "${env.DOCKER_REGISTRY}/chainerist/${env.SERVICE_NAME}"
        MAVEN_IMAGE = 'maven:3.8.4-jdk-11'
    }
     stages {
         stage('build') {
               steps {
                   script {
                           docker.withServer("tcp://${env.JENKINS_DOCKER_HOST}") {
                           docker.image('maven:3.8.4-jdk-11').withRun('-v $HOME/.m2:/root/.m2') {
                              sh 'mvn clean verify -B -U'
                           }
                       }
                   }
               }
           }
            stage('docker-push') {
                    when {
                           branch 'development'
                    }
               		environment {
                           DOCKER_TAG = "0-SNAPSHOT.${env.BUILD_NUMBER}"
               		}
                       steps {
                           script {
                               docker.withServer("tcp://${env.JENKINS_DOCKER_HOST}") {
                                   docker.withRegistry("http://${env.DOCKER_REGISTRY}", "harbor") {
                                       sh "mvn -B -DTAG=${env.DOCKER_TAG} \
                                       	-DTAG_LATEST=snapshot \
                                            dockerfile:build dockerfile:push"
                                   }
                               }
                           }
                       }
            }
            stage('develop-deploy') {
                       when {
                           branch 'development'
                       }
               		environment {
                           DOCKER_TAG = "0-SNAPSHOT.${env.BUILD_NUMBER}"
           			}
                       steps {
                           script {
                               docker.withServer("tcp://${env.DEV_MANAGER_NODE}") {
                                   sh "docker service update --force ${env.SERVICE_NAME} \
                                   --image ${env.DOCKER_REPOSITORY}:${env.DOCKER_TAG}"
                               }
                           }
                       }
            }
        }
       post {
           always {
               echo 'run post when always'
           }
           success {
               echo 'run post when success'
           }
           cleanup {
               cleanWs()

       }
       }
}
