#!/bin/sh

VERSION="4.0.12"

curl  https://groovy.jfrog.io/artifactory/dist-release-local/groovy-zips/apache-groovy-binary-${VERSION}.zip -O apache-groovy-binary-${VERSION}.zip
unzip  -f apache-groovy-binary-4.0.12.zip
./groovy-4.0.12/bin/groovy --version

