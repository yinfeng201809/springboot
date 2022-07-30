pipeline {
    agent any
    tools {
       maven 'mvn-3.8.6'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean'
                sh "printenv"
                echo 'Hello World'
            }
        }
    }
}
