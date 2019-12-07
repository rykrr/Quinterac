#!/bin/bash

frontend="../frontend/target/QuinteracFrontend-1.0-SNAPSHOT.jar"
backend="../backend/target/QuinteracBackend-1.0-SNAPSHOT.jar"

accounts_valid="valid_accounts.txt"
accounts_master="master_accounts.txt"


## Verify Frontend/Backend ####
[[ -f $frontend ]] || { echo "Frontend does not exist"; exit 1; }
[[ -f $backend  ]] || { echo "Backend does not exist";  exit 1; }

## Clear Account Files ########
echo '' > $accounts_valid
echo '' > $accounts_master


## Run daily 5 times ##########

for day in {1..5}; do
	./daily.sh $day $frontend $backend
	
	if [[ $? -ne 0 ]]; then
		echo "Error: Daily script failed on day $day"
		exit 1
	fi
done
