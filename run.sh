#!/bin/bash

# shellcheck disable=SC2164
sh build.sh

docker-compose up handyman-server
