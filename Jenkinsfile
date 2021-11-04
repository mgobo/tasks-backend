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
    }
}