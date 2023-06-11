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
        stage('push container to registry') {
            steps {
                echo "Working directory: ${env.WORKSPACE}"
                script {
                    // docker.withRegistry('https://hub.docker.com', 'fdc6a5d6-91ba-4089-816d-128f97d842cc') {
                    // def customImage = docker.build("mounirelbakkali/demoJenkins:${env.BUILD_ID}")
                    // customImage.push()
                    //}
                    def customImage = docker.build("mounirelbakkali/demojenkins:${env.BUILD_ID}")
                    customImage.push()
    
                } 
            }
        }
    }
}
