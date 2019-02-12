package com.gzd.example.testbasicandroid.tool;

/**
 * Created by gzd on 2019/2/12 0012
 */
public class MyThread extends Thread {
    public CallBack callBack;

    @Override
    public void run() {
        callBack.handleMsg("from callback");
    }

    public interface CallBack{
        void handleMsg(String string);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
