
docker run -d --rm --name=agent1 -p 222:22 \ -e "JENKINS_AGENT_SSH_PUBKEY="  jenkins/ssh-agent:alpine-jdk17
