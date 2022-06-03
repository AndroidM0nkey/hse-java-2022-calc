FROM openjdk
MAINTAINER myNAME
COPY ./build/libs/JavaModCalc-1.0-SNAPSHOT.jar/ /tmp
COPY ./libs/MathLib.jar/ /tmp
COPY ./libs/Operator.jar/ /tmp
COPY ./libs/reflections-0.10.2.jar/ /tmp
COPY ./libs/slf4j-simple-1.7.9.jar/ /tmp
COPY ./libs/slf4j-api-1.7.2.jar/ /tmp
COPY ./libs/javassist.jar/ /tmp
WORKDIR /tmp
CMD ["java", "-cp", ".:JavaModCalc-1.0-SNAPSHOT.jar:reflections-0.10.2.jar:Mathlib.jar:Operator.jar:slf4j-simple-1.7.9.jar:slf4j-api-1.7.2.jar:javassist.jar", "org.example.Main"]


