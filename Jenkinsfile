pipeline{
    agent any
    stages {
        stage("verify branch") {
            steps {
                echo "$GIT_BRANCH"
            }
        }
        stage('maven install') {
            agent {
                docker { image 'maven:3-openjdk-17' }
            }
            steps {
                sh("mvn clean install -DskipTests")
            }
        }
        stage('start tests') {
            agent {
                docker { image 'maven:3-openjdk-17' }
            }
            steps {
                sh("mvn clean test")
            }
            post {
                success {
                    echo "test passed :)"
                }
                failure {
                    echo "test failed :("
                }
            }
        }
        stage('build') {
         agent {
            docker { image 'maven:3-openjdk-17' }
         }
         steps {
            sh("mvn clean package -DskipTests")
            stash name: 'jar', includes: '**/*.jar'
         }
      }
        stage('build docker image') {
            agent {
                docker { image 'maven:3-openjdk-17' }
            }
            steps {
                unstash 'jar'
                sh("docker build -t mounirelbakkali/demojenkins:${env.BUILD_ID} .")
            }
        }
        stage ('push docker image') {
            steps {
                withCredentials([string(credentialsId: 'DockerHub', variable: 'DOCKERHUB')]) {
                    sh("docker login -u mounirelbakkali -p ${DOCKERHUB}")
                    sh("docker push mounirelbakkali/demojenkins:${env.BUILD_ID}")
                }
            }
        }

        // stage('push container to registry') {
        //     steps {
        //         echo "Working directory: ${env.WORKSPACE}"
        //         script {
        //             docker.withRegistry('https://index.docker.io', 'DockerHub') {
        //             def customImage = docker.build("mounirelbakkali/demojenkins:${env.BUILD_ID}")
        //             customImage.push()
        //             }
        //             // def customImage = docker.build("mounirelbakkali/demojenkins:${env.BUILD_ID}")
        //             // customImage.push()
    
        //         } 
        //     }
        // }
    }
}
