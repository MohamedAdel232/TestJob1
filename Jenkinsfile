pipeline {
  agent {
    label 'myagent'
  }

  stages {
    stage('Build Jar Files') {
      steps {
        bat "mvn clean package -DskipTests"
      }
    }

    stage('Build Docker Image') {
      steps {
        bat "docker build -t=muhamed232/jobtest ."
      }
    }

    stage('Push Docker Image') {
      environment {
        DOCKER_HUB = credentials('dockerhub-cred')
      }
      steps {
        bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
        bat "docker push muhamed232/jobtest"
      }
    }
  }

  post {
    always {
      bat "docker logout"
    }
  }
}