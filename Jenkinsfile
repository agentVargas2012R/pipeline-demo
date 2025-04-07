pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: "${CREDENTIALS_ID}", url: "${SCM_URL}", branch: "${BRANCH_NAME}"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

    }
}
