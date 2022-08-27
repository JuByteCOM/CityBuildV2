pipeline {
    agent any
    stages {
        stage("Change Java Version") {
            tools {
               jdk 'jdk16'
            }
            steps {
                echo 'Changing java version'
                sh "java -version"
             }
        }
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
