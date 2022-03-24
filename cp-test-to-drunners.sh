#!/bin/bash

cd ./app/src/test/java/cs345/deadwood/
for ip in "143.198.106.44" "138.68.59.232" ; do
    scp -v *ParserTest.java root@$ip:/home/cs345/test/
done
