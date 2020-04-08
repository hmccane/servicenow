#!/usr/bin/env bash
zip -D -r ./servicenow-android-project.zip . -x '/*app/build/*' -x 'local.properties' -x 'app/local.properties'