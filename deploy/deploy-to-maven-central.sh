#!/usr/bin/env bash

if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_79634f27fd4a_key -iv $encrypted_79634f27fd4a_iv -in deploy/codesigning.asc.enc -out deploy/codesigning.asc -d
    gpg --fast-import deploy/codesigning.asc

    mvn deploy --settings deploy/settings.xml -DperformRelease=true -DskipTests=true
    exit $?
fi
