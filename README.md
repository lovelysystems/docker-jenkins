# Jenkins Automation

This Repo produces a Jenkins Docker container which can be configured via
ENVIRONMENT variables. The localdev/docker-compose.yml file shows an example
of how to use this. The job-dsl folder can be mounted into the container to
create custom jobs at startup.

The .override scripts in src/main/resources/init.groovy.d will be placed inside 
the jenkins home if they're non existing at startup. These scripts can be used 
to configure jenkins in a certain way. New scripts can be added and Jenkins 
will call all groovy scripts within this folder in lexical order. Scripts to
configure an admin user and github credentials are already prepackeged.
