/**
 * This stage lints the Jenkinsfile using npm-groovy-lint.
 */
@CompileStatic
def call() {
    stage('Groovy-lint Jenkinsfile') {
        steps {
            echo '\033[34mLint\033[0m \033[33mJenkinsfile\033[0m \033[35mPipeline\033[0m'
            echo 'Lint..'
            sh 'export PATH="/bin:./node_modules/.bin:$PATH"'
            echo "PATH: $PATH"
            echo "PWD: $PWD"
            sh 'ls -latr'
            sh 'ls -latr ./node_modules/.bin'
            sh './node_modules/.bin/npm-groovy-lint --verbose --parse --format --nolintafter -s Jenkinsfile'
        }
    }
}

