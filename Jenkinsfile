pipeline {
    agent any
    tools {
       jdk 'jdk16'
    }
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
