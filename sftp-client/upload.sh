#!/bin/bash

mkdir -p upload
for i in {1..10}; do echo $(uuidgen) >upload/$(uuidgen); done