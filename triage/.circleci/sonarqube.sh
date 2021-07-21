#!/bin/bash
echo '-----------------------------'
echo ${CIRCLE_BRANCH}
echo ${CIRCLE_BUILD_NUM}
echo ${CIRCLE_PULL_REQUEST}
echo ${CIRCLE_PULL_REQUEST##*/}
echo '-----------------------------'

if [ -n "$CI_PULL_REQUEST" ]; then
     ./gradlew sonarqube --stacktrace \
     -Dsonar.projectName=lhc-disease \
     -Dsonar.login=${DISEASE_SONAR_ACCESS_TOKEN} \
     -Dsonar.github.oauth=${DISEASE_SONAR_GITHUB_ACCESS_TOKEN} \
     -Dsonar.pullrequest.key=${CI_PULL_REQUEST##*/} \
     -Dsonar.pullrequest.base=develop \
     -Dsonar.pullrequest.branch=${CIRCLE_BRANCH} \
     -Dsonar.github.pullRequest=${CI_PULL_REQUEST##*/} \
     -Dsonar.host.url=https://sonar.linecorp.com \
     -Dsonar.projectKey=com.linecorp.healthcare.disease \
     -Dsonar.projectVersion=${CIRCLE_BUILD_NUM} \
     -Dsonar.language=java \
     -Dsonar.java.binaries=./build/ \
     -Dsonar.sourceEncoding=UTF-8 \
     -Dsonar.module=liff-api,admin-api,cp-api,message-api,module/client/reactive/microservice/authentication,module/client/reactive/microservice/core,module/client/reactive/microservice/reactive-core,module/client/servlet/line-login,module/client/servlet/line-message,module/client/servlet/servlet-core,module/encryption/core,module/encryption/jpa,module/encryption/kafka,module/encryption/mongodb,module/exception,module/line-bot,module/web/exception-response,module/web/forwarded-header,module/web/json,module/web/l7check,module/web/reactive-pageable-support,module/web/security,module/web/swagger,microservice/authentication,microservice/survey \
     -Dsonar.github.endpoint=https://git.linecorp.com/api/v3 \
     -Dsonar.github.repository=askme-application/disease \
     -Dsonar.pullrequest.provider=github
else
    ./gradlew sonarqube --stacktrace \
     -Dsonar.projectName=lhc-disease \
     -Dsonar.login=${DISEASE_SONAR_ACCESS_TOKEN} \
     -Dsonar.github.oauth=${DISEASE_SONAR_GITHUB_ACCESS_TOKEN} \
     -Dsonar.host.url=https://sonar.linecorp.com \
     -Dsonar.projectKey=com.linecorp.healthcare.disease \
     -Dsonar.projectVersion=${CIRCLE_BUILD_NUM} \
     -Dsonar.language=java \
     -Dsonar.java.binaries=./build/ \
     -Dsonar.sourceEncoding=UTF-8 \
     -Dsonar.module=liff-api,admin-api,cp-api,message-api,module/client/reactive/microservice/authentication,module/client/reactive/microservice/core,module/client/reactive/microservice/reactive-core,module/client/servlet/line-login,module/client/servlet/line-message,module/client/servlet/servlet-core,module/encryption/core,module/encryption/jpa,module/encryption/kafka,module/encryption/mongodb,module/exception,module/line-bot,module/web/exception-response,module/web/forwarded-header,module/web/json,module/web/l7check,module/web/reactive-pageable-support,module/web/security,module/web/swagger,microservice/authentication,microservice/survey \
     -Dsonar.github.endpoint=https://git.linecorp.com/api/v3 \
     -Dsonar.github.repository=askme-application/disease
fi