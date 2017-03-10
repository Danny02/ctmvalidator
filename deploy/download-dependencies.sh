#!/usr/bin/env bash

wget http://central.maven.org/maven2/co/paralleluniverse/capsule-desktop/0.1.0/capsule-desktop-0.1.0.jar
java -jar capsule-desktop-0.1.0.jar -muw -c notexisting > /dev/null || true

mvn dependency:go-offline -DperformRelease=true -q
