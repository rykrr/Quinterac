#!/bin/bash

pushd frontend/tests
chmod 777 test.sh
status=`bash test.sh`
popd

exit $status
