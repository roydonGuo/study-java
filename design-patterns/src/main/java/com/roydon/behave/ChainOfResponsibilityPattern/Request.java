package com.roydon.behave.ChainOfResponsibilityPattern;

import lombok.Data;

// 请求类
@Data
public class Request {
    private String type;
    private String content;

    public Request(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
