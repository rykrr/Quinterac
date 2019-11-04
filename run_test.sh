#!/bin/bash

pushd frontend/tests
chmod 777 test.sh
`bash test.sh`
status=$?
popd

exit $status
