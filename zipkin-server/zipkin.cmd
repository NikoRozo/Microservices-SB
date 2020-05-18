@echo off
set RABBIT_ADDRESSES=localhost:5672
set STORAGE_TYPE=mysql
SET MYSQL_USER=zipkin
SET MYSQL_PASS=zipkin

java -jar ./zipkin-server-2.21.1-exec.jar