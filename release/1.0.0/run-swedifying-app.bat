@echo off
echo Starting Swedifying the other application ...
set SPRING_PROFILES_ACTIVE=prod
set DATASOURCE_URL=%1
set DATASOURCE_USERNAME=%2
set DATASOURCE_PASSWORD=%3
java -jar swedifying-1.0.0.jar
set DATASOURCE_URL=
set DATASOURCE_USERNAME= 
set DATASOURCE_PASSWORD=