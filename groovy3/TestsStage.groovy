/**
 * This stage runs the tests using Jest.
 */
@CompileStatic
def call() {
    stage('Tests') {
        steps {
            echo '\033[34mTests\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
            echo 'Testing..'
            sh 'export PATH="/bin/:./node_modules/.bin/:$PATH"'
            echo "PATH: $PATH"
            sh 'ls -ltr ./node_modules/.bin'
            sh 'node ./node_modules/jest/bin/jest.js'
        }
    }
}

