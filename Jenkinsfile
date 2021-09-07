pipeline {
    agent any
  //triggers {pollSCM('* * * * *')}
  stages {
    stage('Checkout') {
      steps {
        // Get some code from a GitHub repository
        git branch: "demo1", url: 'https://github.com/astha8293/Project03.git'
      }
    }
        stage('Build') {
      steps {
        sh 'chmod a+x mvnw'
        sh './mvnw clean package -DskipTests=true'
      }
          post {
        always {
          archiveArtifacts 'target/*.jar'
        }
          }
        }
        stage('DockerBuild') {
      steps {
        sh 'docker build -t project03/pass-app:latest .'
      }
        }
         stage('DockerRun') {
      steps {
        sh 'docker run -d --rm -p 9090:9091 project03/pass-app'
      }
        }
  }
}