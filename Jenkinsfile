pipeline {
    agent any
   
    stages {
        stage('Build') {
            steps {
                sh "mvn clean "
                sh "printenv"
                echo 'Hello World'
            }
        }
    }
}
