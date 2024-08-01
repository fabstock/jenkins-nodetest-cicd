/* Requires the Docker Pipeline plugin */
pipeline {
    agent { 
           docker { 
                  image 'node:20.16.0-alpine3.20'
                  args '--user=root'
                  }
             
          }


    // This shows a simple build wrapper example, using the AnsiColor plugin. 
    node {
    
 	// This displays colors using the 'xterm' ansi color map.
    	ansiColor('xterm') {
        // Just some echoes to show the ANSI color.
        stage "\u001B[31mI'm Red\u001B[0m Now not"
    	}
      }

    stages {


        stage('clean'){
        steps { 
             sh 'npm cache clean --force'
            }
        }

        stage('build') {
            steps {
                sh 'node --version'
                
                sh 'npm  install jest --loglevel=verbose'
            }
        }

        stage('Test') {
           steps {
               echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }

    }
}
