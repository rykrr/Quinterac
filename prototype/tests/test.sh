#!/bin/bash

message=(" OK " "FAIL")

run_case() {
	expect expect.exp console_input.txt console_output.txt &>/dev/null
	
	status=$?
	printf 'Test %-3s %-3s    [ %-4s ]\n' $1 $2 ${message[$status]}
	
	[[ $status -eq 0 ]] || exit 1
}

for r in $(ls -dx1 r* | sort -V); do
	pushd $r &>/dev/null
		for t in $(ls -dx1 t* | sort -V); do
			run_case `basename $r` `basename $t`
		done
	popd &>/dev/null
done
