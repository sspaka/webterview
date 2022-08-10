pipeline {
    agent any

    stages {
      stage('Maven Install') {
        agent {
          docker {
            image 'maven:3.5.0'
          }
        }
        steps {
          sh 'mvn clean install'
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
          }
          sh 'docker build -t frontend .'
          sh 'docker run -d -p 8081:8081 frontend'
        }
      }
      stage('Backend Build') {
        steps {
          dir('./webterview_be'){
            echo "here is backend dir"
          }
        }
      }
    }
}