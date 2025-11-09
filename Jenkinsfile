pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository from GitHub...'
                git branch: 'main', url: 'https://github.com/appalanaidu-qa/flipkart.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running TestNG tests (excluding Sikuli)...'
                // Run only non-Sikuli tests in Jenkins
                bat 'mvn test -Dgroups="nonSikuli"'
            }
        }

        stage('Archive Reports') {
            steps {
                echo 'Archiving test reports...'
                // Collect TestNG or Surefire XML reports for Jenkins to display
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed — cleaning workspace.'
            cleanWs()
        }
        failure {
            echo 'Build failed. Check test reports for details.'
        }
        success {
            echo 'Build succeeded. Reports archived successfully.'
        }
    }
}
