# Build from latest LTS version
FROM jenkins/jenkins:lts-alpine
LABEL "maintainer"="Deposit Solutions"
LABEL "github-url"="https://github.com/depositsolutions/jenkins-automation"

ARG httpProxy
ARG noProxy

ENV HTTPS_PROXY=${httpProxy} \
    https_proxy=${httpProxy} \
    HTTP_PROXY=${httpProxy} \
    http_proxy=${httpProxy} \
    ftp_proxy=${httpProxy} \
    FTP_PROXY=${httpProxy} \
    NO_PROXY=${noProxy} \
    no_proxy=${noProxy}

ARG DOCKER_CLI_VERSION="18.06.1-ce"
ENV DOWNLOAD_URL="https://download.docker.com/linux/static/stable/x86_64/docker-$DOCKER_CLI_VERSION.tgz"

USER root

# install docker client
RUN apk --update add curl \
    && mkdir -p /tmp/download \
    && curl -L $DOWNLOAD_URL | tar -xz -C /tmp/download \
    && mv /tmp/download/docker/docker /usr/local/bin/ \
    && rm -rf /tmp/download \
    && rm -rf /var/cache/apk/*

# install applications necessary for builds
RUN apk --update add python3 \
    && rm -rf /var/cache/apk/*

USER jenkins

# Preinstall plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Configure Git
RUN \
  git config --global push.default simple && \
  git config --global user.name "Jenkins" && \
  git config --global user.email jenkins@example.com

# Copy files needed in the container
COPY init.groovy.d/* /usr/share/jenkins/ref/init.groovy.d/
COPY seed-job/ /usr/share/jenkins/ref/jobs/seed-job
COPY scriptApproval.xml.override /usr/share/jenkins/ref/scriptApproval.xml.override
COPY start.sh /usr/bin/

ENTRYPOINT ["/sbin/tini", "--"]
CMD ["/usr/bin/start.sh"]