package com.gzd.example.testbasicandroid.pojo;

/**
 * Created by Administrator on 2019/1/23 0023
 */
public class TestData {
    private String testParam1;
    private String testParam2;

    public TestData(String testParam1, String testParam2) {
        this.testParam1 = testParam1;
        this.testParam2 = testParam2;
    }

    public String getTestParam1() {
        return testParam1;
    }

    public void setTestParam1(String testParam1) {
        this.testParam1 = testParam1;
    }

    public String getTestParam2() {
        return testParam2;
    }

    public void setTestParam2(String testParam2) {
        this.testParam2 = testParam2;
    }
}
