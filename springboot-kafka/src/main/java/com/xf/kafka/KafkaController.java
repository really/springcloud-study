package com.xf.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("sender")
    public String sendMessage(@RequestParam("msg") String msg) {

        String topic = "test";
//        kafkaTemplate.send(topic, msg);
        try {
            msg = LocalDateTime.now() + ":" + msg;

            Message message = new Message();
            message.setId(System.currentTimeMillis());
            message.setMsg(UUID.randomUUID().toString());
            message.setSendTime(LocalDateTime.now());

            msg = JSONObject.toJSONString(message);

            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, "luoye", msg);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println(result.toString());
                    System.out.println("推送消息成功");
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("推送消息失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
