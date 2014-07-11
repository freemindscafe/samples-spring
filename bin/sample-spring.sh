#!/bin/bash

CLASSPATH=""

for jarFileName in `ls ../lib`
do
    CLASSPATH+=:../lib/$jarFileName
done

CLASSPATH+=:../samples-spring.jar

export CLASSPATH

java org.junit.runner.JUnitCore $@
