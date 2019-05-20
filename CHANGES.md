# Changes for docker-jenkins

## 2019-05-20 / 0.0.4

### Feature:

- Install aws cli to copy data to s3. The cli can be configured via ENVIRONMENT variables
  see [aws doc](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-envvars.html)

## 2019-03-13 / 0.0.3

### Feature:

- Install tzdata package to support setting timezones using the `TZ` environment variable


## 2019-01-08 / 0.0.2

### Fix

- docker security credentials are now updated upon startup when changed in env
- nailed jenkins version in Dockerfile to have idempotent builds
- upgraded jenkins base image to 2.157 which fixes 404 errors on build time

## 2018-12-10 / 0.0.1

### Feature:

- Added Slack plugin
- Initial setup
