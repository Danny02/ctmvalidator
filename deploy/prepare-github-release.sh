#!/usr/bin/env bash

VERSION=$(mvn help:evaluate -Dexpression=project.version | egrep -v "INFO|Download")
echo "release version $VERSION to github"

mvn package capsule:build

mv target/ctm-validator-$VERSION-capsule.jar target/ctm-validator-$VERSION.jar

java -jar capsule-desktop-0.1.0.jar -u -c target/ctm-validator-$VERSION.jar
