package com.roydon.behave.ChainOfResponsibilityPattern;

// 具体处理者A
public class ConcreteHandlerA extends Handler {
    @Override
    protected boolean canHandle(Request request) {
        // 判断是否能够处理请求的条件
        return request.getType().equals("A");
    }

    @Override
    protected void processRequest(Request request) {
        // 处理请求的逻辑
        System.out.println("ConcreteHandlerA handles the request: " + request.getContent());
    }
}
