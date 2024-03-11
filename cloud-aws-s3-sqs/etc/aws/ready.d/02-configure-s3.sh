#!/bin/bash

awslocal s3 mb s3://test-bucket
awslocal s3api put-bucket-notification --bucket test-bucket --notification-configuration '{"QueueConfiguration": {"Id": "all", "Event": "s3:ObjectCreated:*", "Queue": "arn:aws:sqs:us-east-1:000000000000:test-queue"}}'
