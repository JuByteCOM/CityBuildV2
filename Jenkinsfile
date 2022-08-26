pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo 'building the application...'
                sh "mvn clean install"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}