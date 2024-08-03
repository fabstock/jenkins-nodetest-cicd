/**
 * This stage clones the repository to the Jenkins workspace.
 */
@CompileStatic
def call() {
    stage('Clone repository') {
        steps {
            checkout scm
            sh 'env'
        }
    }
}

