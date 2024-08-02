/* Requires the Docker Pipeline plugin */

pipeline {
    agent { 
           docker { 
                  image 'node:20.16.0-alpine3.20'
                  args '--user=root -m 512m --cpus=1.5'
                  }
             
          }

    options {
        ansiColor('xterm')
    }

    stages {


        stage('Clean'){
        steps { 
             echo '\033[34mClean\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
             sh 'npm cache clean --force'
            }
        }

        stage('Build') {
            steps {
                echo '\033[34mBuild\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
                sh 'node --version'
                sh 'npm  install jest --loglevel=verbose'
                sh 'npm  install npm-groovy-lint --loglevel=verbose'
                 
            }
        }

        
        stage('Groovy Jenkinsfile'){
             steps{
		echo '\033[34mLint\033[0m \033[33mJenkinsfile\033[0m \033[35mPipeline\033[0m'
                echo 'Lint..'
                export PATH="~/.bin/:$PATH"
                echo "PATH: $PATH"
                echo "PWD: $PWD"
                ls -latr
                sh './node_modules/.bin/npm-groovy-lint  Jenkinsfile'
 
	}
        }


        stage('Test') {
           steps {
               echo '\033[34mTests\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
               echo 'Testing..'
               export PATH="~/.bin/:$PATH"
               sh 'npm test'
               sh './node_modules/.bin/jest  test.sum.js'
               //sh 'jest'
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
           
            steps {
                echo '\033[34mDeploy\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
                echo 'Success'
                echo 'Deploying....'
            }

        }

    }
}
