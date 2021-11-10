#!/bin/bash

# shellcheck disable=SC2164
sh build.sh

# shellcheck disable=SC2103
cd handyman.server/

docker-compose up handyman-server
