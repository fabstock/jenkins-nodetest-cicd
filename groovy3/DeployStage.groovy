/**
 * This stage deploys the application.
 */
//@CompileStatic
def call() {
    stage('Deploy') {
        when {
            expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
            }
        }
        steps {
            echo '\033[34mDeploy\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
            /*
            sshagent(['ssh-credentials-id']) {
                sh '''
                ssh -o StrictHostKeyChecking=no agent1_jenkins@remote-server 'mkdir -p ~/deploy'
                scp -o StrictHostKeyChecking=no  Jenkinsfile agent1_jenkins@192.168.3.84:~/deploy
                ssh -o StrictHostKeyChecking=no agent1_jenkins@192.168.3.84 'echo  1 >file'
                '''
            }
            */
            echo 'Success'
            echo 'Deploying....'
        }
    }
}

