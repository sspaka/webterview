pipeline {
    agent any

    tools {
      maven "M3"
    }

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
            sh 'docker build -t frontend .'
            sh 'docker run -d -p 8081:8081 frontend'
          }
        }
      }
      stage('Backend Build') {
        steps {
          dir('./webterview_be'){
            echo "here is backend dir"
            sh "mvn -Dmaven.test.failure.ignore=true clean package"
            sh 'docker build -t backend .'
            sh 'docker run -d -p 3000:3000 backend'
          }
          post {
            success {
              archiveArtifacts 'target/*.jar'
            }
          }
        }
      }
    }
}