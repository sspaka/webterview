pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
              checkout scm
            }

            post {
                success {
                  echo "success"
                }
                failure {
                  echo "fail"
                }
            }
        }
    }
}