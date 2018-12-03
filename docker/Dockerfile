# Build from latest LTS version
FROM jenkins/jenkins:lts-alpine
LABEL "maintainer"="lovelysystems"
LABEL "github-url"="https://github.com/lovelysystems/docker-jenkins"

ENV DOCKER_CLI_VERSION="18.06.1-ce"
ENV DOCKER_DOWNLOAD_URL="https://download.docker.com/linux/static/stable/x86_64/docker-$DOCKER_CLI_VERSION.tgz"

USER root

# install docker client
RUN apk --update add curl sudo \
    && mkdir -p /tmp/download \
    && curl -L $DOCKER_DOWNLOAD_URL | tar -xz -C /tmp/download \
    && mkdir /opt \
    && mv /tmp/download/docker/docker /opt/ \
    && echo "jenkins ALL=NOPASSWD: /opt/docker" > /etc/sudoers.d/docker \
    && chmod 0440 /etc/sudoers.d/docker \
    && rm -rf /tmp/download \
    && rm -rf /var/cache/apk/*

# Create docker shim
COPY docker /usr/local/bin/docker

USER jenkins

# Preinstall plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt


# Configure git
RUN git config --global push.default simple \
    && git config --global user.name "Jenkins" \
    && git config --global user.email jenkins@example.com

# Copy files needed in the container
COPY init.groovy.d/* /usr/share/jenkins/ref/init.groovy.d/
COPY seed-job/ /usr/share/jenkins/ref/jobs/seed-job
COPY scriptApproval.xml.override /usr/share/jenkins/ref/scriptApproval.xml.override
COPY start.sh /usr/bin/

ENTRYPOINT ["/sbin/tini", "--"]
CMD ["/usr/bin/start.sh"]
