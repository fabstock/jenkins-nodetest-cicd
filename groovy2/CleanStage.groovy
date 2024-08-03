/**
 * This stage cleans the npm cache.
 */
@CompileStatic
def call() {
    stage('Clean') {
        steps {
            echo '\033[34mClean\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
            sh 'npm cache clean --force'
        }
    }
}

