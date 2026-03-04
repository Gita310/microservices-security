pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        DB_USERNAME = credentials('db-username')
        DB_PASSWORD = credentials('db-password')
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/Gita310/microservices-security.git'
            }
        }

        stage('Build Parent Project') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t auth-service ./auth-service'
                sh 'docker build -t order-service ./order-service'
                sh 'docker build -t gateway-service ./gateway-service'
                sh 'docker build -t discovery-service ./discovery-service'
                sh 'docker build -t config-server ./config-server'
            }
        }
    }

    post {
        success {
            echo 'Build Successful 🚀'
        }
        failure {
            echo 'Build Failed ❌'
        }
    }
}