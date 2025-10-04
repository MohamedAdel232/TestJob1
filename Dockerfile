FROM bellsoft/liberica-openjdk-alpine:23-cds

RUN apk add curl jq

WORKDIR /home/MyProject

ADD target/DockerResources .
ADD runner.sh runner.sh

RUN dos2unix runner.sh

ENTRYPOINT sh runner.sh
