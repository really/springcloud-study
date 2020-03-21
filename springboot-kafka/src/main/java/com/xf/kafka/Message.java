package com.xf.kafka;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {

    private Long id;

    private String msg;

    private LocalDateTime sendTime;
}
