/**
 * This stage installs the required npm packages and sets up the environment.
 */
@CompileStatic
def call() {
    stage('Build') {
        steps {
            echo '\033[34mBuild\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
            sh 'node --version'
            sh 'npm  install jest'
            sh 'npm  install npm-groovy-lint'
            sh 'apk add openjdk17-jre curl'
            sh './install-groovy.sh'
        }
    }
}

