pipeline {
    agent any
    tools {
       maven 'mvn-3.6.0'
    }
    stages {
        stage('Build') {
            steps {
                bat "mvn clean install "
                echo 'Hello World'
            }
        }
    }
}
