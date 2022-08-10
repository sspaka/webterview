pipeline {
    agent any

    stages {
        stage('Prepare') {
            steps {
              checkout scm
            }
            post {
                success {
                  echo " prepare success"
                }
                failure {
                  echo "prepare fail"
                }
            }
        }
        stage('Frontend Build') {
          steps {
            dir('frontend'){
              echo "here is frontend dir"
            }
          }
        }
    }
}