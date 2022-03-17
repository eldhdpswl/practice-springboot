package dev.eldhdpswl.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//application.yml의 profile의 상태에 따라 bean이 생성이 될지 확인해야된다.
@Profile("!local")
public class RabbitMQ implements MessageQueueInterface{
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQ.class);

    public RabbitMQ(){
        logger.info("rabbitmq component");
    }

    @Override
    public String readMessage() {
        // code for commnicating with RabbitMQ
        return "message from rabbitmq";
    }
}
