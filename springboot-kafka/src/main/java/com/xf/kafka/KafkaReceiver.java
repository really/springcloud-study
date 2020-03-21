package com.xf.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaReceiver {

    @KafkaListener(topics = "test", groupId = "test-consumer-group")
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

//            String msg = record.value().toString(); //(String) kafkaMessage.get();
//            log.info("kafkaMessage {}", kafkaMessage.get().toString());
//            log.info("kafkaMessage2 {}", JSONObject.parseObject(kafkaMessage.get().toString(), Message.class));
//
//
//            Message message = JSONObject.parseObject(msg, Message.class);
//            log.info("message {}", message);

//            System.out.printf("partition = %s, key = %s \n", record.partition(), record.key());
            System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
        }
    }
}
