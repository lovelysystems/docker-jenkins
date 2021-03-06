#!/usr/bin/env bash

SCRIPT_PATH=/usr/share/jenkins/ref/jobs/seed-job/workspace/job.groovy.override
APPROVAL_PATH=/usr/share/jenkins/ref/scriptApproval.xml.override

# Compute job.groovy script hash and replace placeholder in scriptApproval.xml.
SCRIPT_HASH=$((echo -n 'groovy:'; cat ${SCRIPT_PATH}) | sha1sum | awk '{print $1}')
sed -ie "s/\${job\.groovy\.hash}/${SCRIPT_HASH}/g" ${APPROVAL_PATH}
unset SCRIPT_HASH

# Remove old scriptApproval.xml and job.groovy.
# The dsl file is our source of truth not the webUI
rm -rf $JENKINS_HOME/jobs/seed-job/workspace/job.groovy
rm -rf $JENKINS_HOME/scriptApproval.xml

/usr/local/bin/jenkins.sh $@
