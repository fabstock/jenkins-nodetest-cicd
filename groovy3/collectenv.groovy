import jenkins.model.*

collectBuildEnv = [:]

@NonCPS
def getNodes(String label) {
    jenkins.model.Jenkins.instance.nodes.collect { thisAgent ->
        if (thisAgent.labelString.contains("${label}")) {
        // this works too
        // if (thisAagent.labelString == "${label}") {
            return thisAgent.name
        }
    }
}

def dumpBuildEnv(String agentName) {
    node("${agentName}") {
        stage("Env in ${agentName}") {
            echo "running on agent, ${agentName}"
            sh 'printenv'
        }
    }
}

def processTask() {
    // Replace label-string with the label name that you may have
    def nodeList = getNodes("label-string")
    
    for(i=0; i<nodeList.size(); i++) {
        def agentName = nodeList[i]
        
        // skip the null entries in the nodeList
        if (agentName != null) {
            println "Prearing task for " + agentName
            collectBuildEnv["node_" + agentName] = {
                dumpBuildEnv(agentName)
            }
        }
    }
}

