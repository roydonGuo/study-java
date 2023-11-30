package com.roydon.behave.ChainOfResponsibilityPattern;

// 抽象处理者
public abstract class Handler {
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Request request) {
        if (canHandle(request)) {
            processRequest(request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available for the request.");
        }
    }

    protected abstract boolean canHandle(Request request);

    protected abstract void processRequest(Request request);
}
