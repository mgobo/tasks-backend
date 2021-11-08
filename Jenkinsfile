pipeline {
    agent any
    stages {
        stage('Build Backend') {
            steps {
                echo 'mvn clean:clean package -DskipTests=true'
            }
        }
        stage('Unit Tests...') {
            steps {
                echo 'mvn test'
            }
        }
        stage('Sonar Analysis....') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withMaven(jdk: 'JDK8', maven: 'maven-jenkins') {
                    withSonarQubeEnv(installationName:"sonar-ghost", credentialsId: 'SonarGhostCredentials') {
                        echo "${scannerHome}"
                        echo 'mvn sonar:sonar'
                    }
                }
            }
        }
            
    }
}