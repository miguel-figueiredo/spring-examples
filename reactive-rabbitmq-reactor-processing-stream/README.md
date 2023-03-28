# RabbitMQ Processing Stream

## Install rabbitmq

`brew install rabbitmq`

## Send messages

`for i in $(seq 1000); do rabbitmqadmin publish exchange=test-exchange routing_key=in-queue payload="$i"; done`

## Receive messages

`while true; do rabbitmqadmin get queue=out-queue ackmode=ack_requeue_false; done`