pipeline {
    agent any

    tools {
      maven "M3"
    }

    stages {
      stage('Docker kill'){
        steps {
          sh 'docker stop fe || true && docker rm fe || true'
          sh 'docker stop be || true && docker rm be || true' 
        }
        post {
          success {
            echo "kill success"
          }
          failure {
            echo "kill fail"
          }
        }
      }
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
            sh 'docker build -t frontend .'
            sh 'docker run -d --name fe -p 8081:80 frontend'
          }
        }
      }
      stage('Backend Build') {
        steps {
          dir('./webterview_be'){
            echo "here is backend dir"
            sh "mvn -Dmaven.test.failure.ignore=true clean package"
            sh 'docker build -t backend .'
            sh 'docker run -d --name be -p 3000:3000 backend'
          }
        }
      }
    }
}
