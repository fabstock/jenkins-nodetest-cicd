/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'node:20.16.0-alpine3.20' } }
    stages {


        stage('clean'){
        steps { 
             sh 'npm cache clean --force'
            }
        }

        stage('build') {
            steps {
                sh 'node --version'
                
                sh 'npm  install jest'
            }
        }

        stage('Test') {
           steps {
               echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }

    }
}
