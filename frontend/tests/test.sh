#!/bin/bash

dir=`pwd`
message=("OK" "FAIL")

clean() {
	rm -f ../../output_transactions.txt
	rm -f ../../log.txt
}

fail() {
	echo "[ FAIL ]"
	if [[ $1 -eq 0 ]]; then
		echo "Consult log.txt for more details" 
		cat ../../log.txt
		rm -f ../../output_transactions.txt
	else
		echo "Transaction outputs differ"
	fi
	exit 1
}

run_case() {
	clean
	printf 'Test %-3s %-3s ' $1 $2
	
	expect $dir/expect.exp console_output.txt console_input.txt &>/dev/null
	
	[[ $? -eq 0 ]] || fail 0
	
	if [[ -e expected_transactions.txt ]]; then
		diff expected_transactions.txt ../../output_transactions.txt &>/dev/null
		
		if [[ $? -ne 0 ]]; then
			fail 1
		fi
	fi
	
	clean
	echo "[ OK   ]"
}

for r in $(ls -dx1 r* | sort -V); do
	pushd $r &>/dev/null
		for t in $(ls -dx1 t* | sort -V); do
			pushd $t &>/dev/null
				sed -E -i "s/[‘’]/'/g" console_output.txt
				sed -E -i "s/Please enter login type/Please enter a login type/g" console_output.txt
				sed -E -i "s/Successfully logged in as machine\./logged in as machine/g" console_output.txt
				sed -E -i "s/Successfully logged in as agent\./logged in as agent/g" console_output.txt
				sed -E -i "s/You have successfully logged out/logged out/g" console_output.txt
				sed -E -i "s/Successfully logged out/logged out/g" console_output.txt
				sed -E -i "s/atm>/machine>/g" console_output.txt
				echo '1000000' > accounts.txt
				echo '2000000' >> accounts.txt
				echo '2000001' >> accounts.txt
				echo '0000000' >> accounts.txt
				run_case `basename $r` `basename $t`
			popd &>/dev/null
		done
	popd &>/dev/null
done

rm -f log.txt
exit 0
