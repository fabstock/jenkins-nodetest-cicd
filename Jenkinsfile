//import groovy.transform.CompileStatic
//@GrailsCompileStatic, @CompileStatic or @CompileDynamic  CompileStatic
//@CompileStatic

/* Requires the Docker Pipeline plugin */
//Jenkinsfile (Declarative Pipeline)

pipeline {
    //agent { dockerfile true }
    //node { label 'Noeud1' }
    agent {
        docker {
            //label 'Agent1-cloud1-Noeud1'
            image 'node:20.16.0-alpine3.20'
            //image 'node:20.16.0-slim'
            //args '--user=root -m 512m --cpus=1.5'
            args '--user=root  -m 512m --cpus=1.5'
            ///args 'NODE_ENV=node'
            // Run the container on the node specified at the
            // top-level of the Pipeline, in the same workspace,
            // rather than on a new node entirely:
            reuseNode true
        }
    }
    environment {
        JAVA_HOME = '/usr'
    }
    options {
        ansiColor('xterm')
    }
    stages {
        stage('Clone repository') {
            /* Let's make sure we have the repository cloned to our workspace */
            steps {
                checkout scm
                sh 'env'
            }
        }

        stage('demo') {
            steps {
                // Using xterm
                ansiColor('xterm') {
                    echo '\033[31;1m Executing demo stage: ansicolor xterm \033[0m'
                }

                // Using vga
                ansiColor('vga') {
                    echo '\033[31;1m command-1 ansicolor vga\033[0m'
                }

                echo '\033[31;1m command-2 \033[0m'
            }
        }

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
                //sh 'npm  install jest --loglevel=verbose'
                sh 'npm  install jest'
                //sh 'npm  install npm-groovy-lint --loglevel=verbose'
                sh 'npm  install npm-groovy-lint'
                sh 'apk add openjdk17-jre curl openssh-client bash'
                sh './install-groovy.sh'
            }
        }

        stage('Groovy-lint Jenkinsfile') {
            steps {
                echo '\033[34mLint\033[0m \033[33mJenkinsfile\033[0m \033[35mPipeline\033[0m'
                echo 'Lint..'
                sh 'export PATH="/bin:./node_modules/.bin:$PATH"'
                echo "PATH: $PATH"
                echo "PWD: $PWD"
                sh 'ls -latr'
                sh 'ls -latr ./node_modules/.bin'
                //sh './node_modules/.bin/npm-groovy-lint --verbose 1 --format --parse  Jenkinsfile'
                //sh './node_modules/.bin/npm-groovy-lint --format --parse  Jenkinsfile'
                sh './node_modules/.bin/npm-groovy-lint --verbose --parse  --format --nolintafter  -s Jenkinsfile'
            }
        }

        stage('Tests') {
            steps {
                echo '\033[34mTests\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
                echo 'Testing..'
                sh 'export PATH="/bin/:./node_modules/.bin/:$PATH"'
                echo "PATH: $PATH"
                sh 'ls -ltr ./node_modules/.bin'
                //sh 'npm test'
                sh 'node ./node_modules/jest/bin/jest.js'
            //sh '~/.bin/jest  test.sum.js'
            //sh 'jest'
            }
        }

        stage('Release') {
            //when { tag pattern: "release-\\d+", comparator: "REGEXP"}
            steps {
                echo 'Release Staging'
            //echo $branch
            }
        }
        stage('Deploy1') {
            steps {
                 withCredentials([[$class: 'SSHUserPrivateKeyBinding', credentialsId: 'agent1', keyFileVariable: 'SSH_PRIVATE_KEY', passphraseVariable: '', usernameVariable: 'SSH_USERNAME']]) {
                     sh 'ssh-agent /bin/bash'
                     sh """
                     eval \$(ssh-agent) && ssh-add ${SSH_PRIVATE_KEY} && ssh-add -l &&
                     ENVIRONMENT=${env.ENVIRONMENT} \
                     PLAYBOOK=${env.PLAYBOOK} \
                     BASTION_USER=${env.BASTION_USER} \
                     BASTION_HOST=${env.BASTION_HOST} \
                     env
                     """
                     //./deploy-ansible.sh
               }
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

                /*
                */
                //sshagent(['agent1-ssh)']) {
                //sshagent(['ssh-credential-id']) {
                sshagent(['agent1']) {
                    sh '''
                       ssh -o StrictHostKeyChecking=no agent1_jenkins@192.168.3.84 "mkdir -p ~/deploy"
                       scp -o StrictHostKeyChecking=no  Jenkinsfile agent1_jenkins@192.168.3.84:~/deploy
                       ssh -o StrictHostKeyChecking=no agent1_jenkins@192.168.3.84 "echo  1 >file"
                       '''
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
