package com.roydon.adapter.object_adapter;

public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        String msg = "sd card read a msg :hello word SD";
        return msg;
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("sd card write msg : " + msg);
    }
}
