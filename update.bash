#!/bin/bash

cd ../dawg6-common
pwd
git pull
cd ../dawg6-gwt-common
pwd
git pull
cd ../dawg6-d3api
pwd
git pull
cd ../dhcalc
pwd
git pull
ant
scp dhcalc.war pi@dawg6-pi-2:
