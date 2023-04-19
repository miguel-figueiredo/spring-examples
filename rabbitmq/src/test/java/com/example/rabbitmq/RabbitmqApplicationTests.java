package com.example.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.mockito.LatchCountDownAndCallRealMethodAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RabbitListenerTest
@ExtendWith(RabbitMQExtension.class)
class RabbitmqApplicationTests {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private RabbitListenerTestHarness harness;

	@Test
	void test() throws InterruptedException {
		Consumer consumer = this.harness.getSpy("listener");
		LatchCountDownAndCallRealMethodAnswer answer = this.harness.getLatchAnswerFor("listener", 1);
		doAnswer(answer).when(consumer).listen(anyString());

		template.convertAndSend("myQueue", "message");

		answer.await(10);
		verify(consumer).listen("message");
	}

}
