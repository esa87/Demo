#!/bin/bash

cd /restsuo/target
# turn on bash's job control



# Start the primary process and put it in the background
/restsuo/allure-2.25.0/bin/allure serve -p 5050 &

# Start the helper process
#./my_helper_process

# the my_helper_process might need to know how to wait on the
# primary process to start before it does its work and returns


# now we bring the primary process back into the foreground
# and leave it there
#fg %1