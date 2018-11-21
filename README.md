# Jenkins Automation

This Repo Produces a jenkins container which takes a job-dsl file to create all
jobs automatically at startup. The file must be placed inside the container at
the following location:
`/usr/share/jenkins/ref/jobs-seed-job/workspace/job.groovy.override`

## Plugins

All plugins inside `docker/plugins.txt` will be installed during the
container build and are available when the container is started.

## Configuration

### init scripts

The `.override` scripts in `docker/init.groovy.d` will be placed inside 
the jenkins home if they're non existing at startup. These scripts can be used 
to configure jenkins in a certain way. New scripts can be added and Jenkins 
will call all groovy scripts within this folder in lexical order. Scripts to
configure an admin user and github/ssh credentials are already prepackaged.

### Admin Account

| JENKINS_ADMIN_PASSWORD | Password for admin account |
