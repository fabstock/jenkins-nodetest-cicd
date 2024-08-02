void call() {
    stage('Build') {
        steps {
            echo '\033[34mBuild\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
            sh 'node --version'
            sh 'npm install jest --loglevel=verbose'
            sh 'npm install npm-groovy-lint --loglevel=verbose'
            sh 'apk add openjdk17-jre'
        }
    }
}

