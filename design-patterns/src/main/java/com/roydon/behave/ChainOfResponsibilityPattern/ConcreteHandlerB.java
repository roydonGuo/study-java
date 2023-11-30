package com.roydon.behave.ChainOfResponsibilityPattern;

// 具体处理者B
public class ConcreteHandlerB extends Handler {
    @Override
    protected boolean canHandle(Request request) {
        // 判断是否能够处理请求的条件
        return request.getType().equals("B");
    }

    @Override
    protected void processRequest(Request request) {
        // 处理请求的逻辑
        System.out.println("ConcreteHandlerB handles the request: " + request.getContent());
    }
}
