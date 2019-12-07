#!/bin/bash

day=$1

frontend=$2
backend=$3

inputs="inputs/${day}"
outputs="outputs/${day}"

merged="outputs/${day}/merged_summary.txt"

accounts_valid=${4:-"valid_accounts.txt"}
accounts_master=${5:-"master_accounts.txt"}

overrides=$(grep "${day}/" overrides.txt)


## Fail ##################
fail() {
	echo "Error: $1 (Session $2)"
	exit 1
}


## Make Outputs ##########
mkdir -p $outputs

## Copy Inputs ###########
cp $accounts_valid ${outputs}/${accounts_valid}
cp $accounts_valid ${outputs}/${accounts_valid}.bck
cp $accounts_master ${outputs}/${accounts_master}
cp $accounts_master ${outputs}/${accounts_master}.bck


## Run Frontend ##########
touch $merged

for session in {1..3}; do
	mkdir -p $outputs/$session
	
	id="${day}/${session}"
	
	input="${inputs}/${session}/input.txt"
	output="${outputs}/${session}/summary.txt"
	
	[[ -f $input ]] || fail "No input transactions" $session;
	
	if [[ $overrides == *$id* ]]; then
		echo "=============================="
		echo "==   Manual Override Mode   =="
		echo "=============================="
		echo
		echo "Day ${day}, Session ${session}"
		echo
		echo "=============================="
		echo
		java -jar $frontend $accounts_valid $output
	else
		expect daily.exp $frontend $accounts_valid $input $output
		sleep 5
	fi
	
	
	[[ $? -ne 0 ]] && fail "Frontend process failed" $session
	
	grep "Error:" log.txt
	[[ $? -ne 0 ]] && fail "Error detected in logs" $session
	
	[[ -f $output ]] && sed /EOS/d < $output >> $merged
done

echo "EOS 0000000 000 0000000 ***" >> $merged


## Run Backend ###########
java -jar $backend $merged ${outputs}/${accounts_master} ${outputs}/${accounts_valid}

if [[ $? -ne 0 ]]; then
	echo "Error: Day ${day} backend has failed"
	exit 1
fi


## Copy Outputs ##########
cp ${outputs}/${accounts_master} ${accounts_master}
cp ${outputs}/${accounts_valid} ${accounts_valid}
