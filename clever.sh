#!/bin/bash
APP_NAME=test-gga-java17
clever create --type gradle $APP_NAME # --org orga_716f9d52-e22e-4521-b67c-dd98206a6a59
clever scale --flavor pico --max-instances 1
clever env set CC_JAVA_VERSION  17
clever env set GRADLE_BUILD_GOAL "build -x test -Dquarkus.package.type=native -Dquarkus-profile=dev -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:21.3-java17"
clever env set GRADLE_DEPLOY_GOAL native
clever env set CC_RUN_COMMAND "build/code-with-quarkus-1.0.0-SNAPSHOT-runner -Dquarkus-profile=dev"
clever deploy