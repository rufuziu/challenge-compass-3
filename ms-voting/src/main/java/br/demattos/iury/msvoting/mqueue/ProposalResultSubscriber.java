package br.demattos.iury.msvoting.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProposalResultSubscriber {
  @RabbitListener(queues = "${mq.queue.voting-result}")
  public void recieveProposalResult(
          @Payload
          String payload) {
    System.out.println(payload);
  }
}
