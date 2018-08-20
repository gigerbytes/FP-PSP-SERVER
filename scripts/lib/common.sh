#!/bin/bash
DATE_TIME="`date +%F_%H_%M`"
export DATE_TIME

function check_status() {
    status=$1
    if [ ${status} -ne 0 ]; then
        echo "Program exited with code: $status"
	_EXIT_STATUS=${status}
	exit_handler
    fi
}

function exit_handler(){
    exit $_EXIT_STATUS
}

type mvn >/dev/null 2>&1 || { echo >&2 "ERROR: I require 'mvn' but it's not installed. Aborting."; exit 1; }

[ -z "$RUN_PROFILE" ] && echo "ERROR: Spring running RUN_PROFILE not defined. Aborting. Example: export RUN_PROFILE=dev" && exit 1

#[ $# -eq 0 ] && echo "ERROR: No parameter provided" && exit 2

sys=$(uname);
if [ $sys != "MINGW32_NT-6.2" ]; then
    MY_IP="`/sbin/ifconfig | awk '$1 == "inet" { print $2 }'|head -1 | awk -F\: '{print $2}'`";
else
    MY_IP="unknown";
fi
# si estamos dentro de sodep
for i in `scripts/lib/get-ip.sh`; do 
	if [[ "$i" =~ ^10\.1\.* || "$MY_IP" =~ ^10\.0\.* ]]; then 
		SODEP="si"; 
	else 
		SODEP=""; 
	fi 
done
PROP_FILE=src/main/resources/application-${RUN_PROFILE}.properties

URL=$(grep ^spring.datasource.url $PROP_FILE | cut -d '=' -f 2)
HOSTPORT=$(echo $URL |  cut -d '/' -f 3)
PORT=$(echo $HOSTPORT | awk -F: '{print $2}')
HOST=$(echo $HOSTPORT | cut -d '\' -f 1)
DB=$(echo $URL | cut -d '/' -f 4)

if [ -z $PORT ]; then
	PORT=5432
fi

USERNAME=$(grep ^spring.datasource.username $PROP_FILE | cut -d ':' -f 2)
PASSWORD=$(grep ^spring.datasource.password $PROP_FILE | cut -d ':' -f 2)

export PGPASSWORD=$PASSWORD
export MY_IP

