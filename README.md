# Jenkins Automation

This Repo Produces a Jenkins container which takes a job-dsl file to create all
jobs automatically at startup. To do this place the file at the following 
location: `/usr/share/jenkins/ref/jobs-seed-job/workspace/job.groovy.override`

## Plugins

Install plugins by putting them into the `docker/plugin.txt` file. All the 
referenced plugins are installed during the container build. 

## Configuration

### init scripts

The `.override` scripts in `docker/init.groovy.d` will be placed inside the 
Jenkins home if they do not exist at startup. These scripts can be used to 
configure Jenkins in a certain way. New scripts can be added, and Jenkins 
will call all groovy scripts within this folder in lexical order. Scripts to
configure an admin user and GitHub/ssh credentials are already prepackaged.

### Admin Account

The admin account has password `admin` as default. This can be overridden by
setting the `JENKINS_ADMIN_PASSWORD` env-variable to the desired value

### SSH

A init script is available which creates global credentials for ssh named
`ssh-credentials.` The script takes either username + password or 
username + key file. For key authentication make the key file available 
in the container at the following path:  `/run/secrets/jenkins_ssh_key`

To use ssh in the example symlink your ssh-key to `localdev/.ssh/id`

The ssh credentials can then be used in a job with the ssh-agent wrapper
```job-dsl
job('example') {
    wrappers {
        sshAgent('ssh-credentials')
    }
}
```
