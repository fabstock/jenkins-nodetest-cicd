void call() {
    stage('Tests') {
        steps {
            echo '\033[34mTests\033[0m \033[33mStage\033[0m \033[35mPipeline\033[0m'
            echo 'Testing..'
            sh 'export PATH="~/.bin/:node_modules/.bin/:$PATH"'
            echo "PATH: $PATH"
            sh 'ls -latr ./node_modules/.bin'
            sh 'npm test'
            sh 'node /home/fab/LAMANU/TESTs/node_modules/jest/bin/jest.js'
        }
    }
}

