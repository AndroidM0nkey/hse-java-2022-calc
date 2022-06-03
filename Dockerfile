FROM openjdk
MAINTAINER myNAME
COPY ./build/libs/JavaModCalc-1.0-SNAPSHOT.jar/ /tmp
COPY ./libs/reflections-0.10.2.jar/ /tmp
WORKDIR /tmp


