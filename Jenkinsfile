/* Requires the Docker Pipeline plugin */

pipeline {
    agent {
        docker {
            image 'node:20.16.0-alpine3.20'
            args '--user=root -m 512m --cpus=1.5'
        }
    //environment {
    //    PATH = "./node_modules/.bin/:${env.PATH}"
    //}
    }

    options {
        ansiColor('xterm')
    }

    stages {
        stage('Clean') {
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
                sh 'apk add openjdk17-jre'
            }
        }

        stage('Groovy-lint Jenkinsfile') {
            steps {
                echo '\033[34mLint\033[0m \033[33mJenkinsfile\033[0m \033[35mPipeline\033[0m'
                echo 'Lint..'
                //export PATH="~/.bin/:node_modules/.bin/:$PATH"
                echo "PATH: $PATH"
                echo "PWD: $PWD"
                sh 'ls -latr'
                sh './node_modules/.bin/npm-groovy-lint --format --parse  Jenkinsfile'
            }
        }

        stage('Tests') {
            steps {
                echo '\033[34mTests\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
                echo 'Testing..'
                echo "PATH: $PATH"
                //export PATH="~/.bin/:node_modules/.bin/:$PATH"
                //sh 'npm test'
                sh './node_modules/.bin/jest  test.sum.js'
            //sh 'jest'
            }
        }

        stage('Deploy') {
            agent {
                label 'agent1_jenkins' // Nom de l'agent SSH configuré dans Jenkins
            }
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                echo '\033[34mDeploy\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'

                sshagent(['ssh-credentials-id']) {
                    sh """
                    ssh -o StrictHostKeyChecking=no agent1_jenkins@remote-server 'mkdir -p ~/deploy'
                    scp -o StrictHostKeyChecking=no  Jenkinsfile agent1_jenkins@192.168.3.84:~/deploy
                    ssh -o StrictHostKeyChecking=no agent1_jenkins@192.168.3.84 'echo  1 >file'
                    """
                }

                echo 'Success'
                echo 'Deploying....'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

