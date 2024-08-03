/**
 * This stage demonstrates the use of ANSI color codes.
 */
@CompileStatic
def call() {
    stage('Demo') {
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
}

