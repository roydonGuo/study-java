package com.roydon.behave.ChainOfResponsibilityPattern;

/**
 * Client
 *
 * @AUTHOR: roydon
 * @DATE: 2023/11/30
 * 责任链模式包含以下角色：
 * <p>
 * 抽象处理者（Handler）：定义了处理请求的接口，并持有下一个处理者的引用。通常包含一个处理方法来处理请求，以及一个设置下一个处理者的方法。
 * <p>
 * 具体处理者（ConcreteHandler）：实现了抽象处理者接口，具体处理请求的逻辑。如果自己无法处理请求，可以将请求传递给下一个处理者。
 * <p>
 * 使用责任链模式可以实现请求的解耦和动态处理。当一个请求需要经过多个处理者进行处理时，责任链模式可以避免请求发送者与接收者之间的直接耦合，提高代码的灵活性和可扩展性。
 **/
public class Client {
    public static void main(String[] args) {
        // 创建处理者
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();

        // 设置处理者的下一个处理者
        handlerA.setNextHandler(handlerB);

        // 创建请求
        Request request1 = new Request("A", "Request 1");
        Request request2 = new Request("B", "Request 2");
        Request request3 = new Request("C", "Request 3");

        // 处理请求
        handlerA.handleRequest(request1);
        handlerA.handleRequest(request2);
        handlerA.handleRequest(request3);
    }
}
