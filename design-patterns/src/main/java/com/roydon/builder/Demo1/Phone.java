package com.roydon.builder.Demo1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Phone
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/10
 **/
@ToString
public class Phone {
    private String cpu;
    private String memory;
    private String mainBoard;
    private String buttery;

    public Phone(Builder builder) {
        this.cpu = builder.cpu;
        this.memory = builder.memory;
        this.mainBoard = builder.mainBoard;
        this.buttery = builder.buttery;
    }

    public static class Builder {
        private String cpu;
        private String memory;
        private String mainBoard;
        private String buttery;

        public Builder() {
        }

        public Builder cpu(String s) {
            cpu = s;
            return this;
        }

        public Builder memory(String s) {
            memory = s;
            return this;
        }

        public Builder mainBoard(String s) {
            mainBoard = s;
            return this;
        }

        public Builder buttery(String s) {
            buttery = s;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }

    }

}
