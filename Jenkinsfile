/* Requires the Docker Pipeline plugin */
pipeline {


    agent { 
           docker { 
                  image 'node:20.16.0-alpine3.20'
                  args '--user=root -m 512m --cpus=1.5'
                  }
             
          }



    stages {


        stage('clean'){
        steps { 
             sh 'npm cache clean --force'
            }
        }

        stage('build') {
            steps {
                sh 'node --version'
                
                sh 'npm  install jest --loglevel=verbose'
            }
        }

        stage('Test') {
           steps {
               echo 'Testing..'
              //sh 'npm test'
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
           
            steps {
                echo 'Success'
                echo 'Deploying....'
            }

        }

    }
}
