node {
  // https://support.cloudbees.com/hc/en-us/articles/115001595227-How-To-Specify-A-Specific-JDK-In-Pipeline-
  // configure the JDK in Manage Jenkins > Global Tool Configuration > JDK
  jdk = tool name: 'jdk16'
  env.JAVA_HOME = "${jdk}"

  echo "jdk installation path is: ${jdk}"

  // next 2 are equivalents
  sh "${jdk}/bin/java -version"

  // note that simple quote strings are not evaluated by Groovy
  // substitution is done by shell script using environment
  sh '$JAVA_HOME/bin/java -version'
}
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
