pipeline {
    agent any
    tools {
       maven 'mvn-3.6.0'
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn clean"
                sh "printenv"
                echo 'Hello World'
            }
        }
    }
}
