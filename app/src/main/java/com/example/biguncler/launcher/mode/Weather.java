package com.example.biguncler.launcher.mode;

/**
 * Created by Biguncler on 11/03/2017.
 */

public class Weather {

    /*      "tq1": "多云",  //白天天气
            "tq2": "晴",  //夜间天气，当与白天天气相同时，两者可合并为一个
            "numtq1": "01",  //白天天气编码
            "numtq2": "00",  //夜间天气编码
            "qw1": "6",  //白天气温
            "qw2": "-5",  //夜间气温
            "fl1": "3-4级",  //白天风力
            "numfl1": "1",  //白天风力编码
            "fl2": "微风",  //夜间风力
            "numfl2": "0",  //夜间风力编码
            "fx1": "北风",  //白天风向
            "numfx1": "8",  //白天风向编码
            "fx2": "无持续风向",  //夜间风向，如白天风力风向与夜间风力风向一致，可合并为一行
            "numfx2": "0",  //夜间风向编码
            "date": "2016-03-09"  //预报日期*/



    private String tq1;
    private String tq2;
    private String qw1;
    private String qw2;
    private String date;
    private int iconId;


    public String getTq1() {
        return tq1;
    }

    public void setTq1(String tq1) {
        this.tq1 = tq1;
    }

    public String getTq2() {
        return tq2;
    }

    public void setTq2(String tq2) {
        this.tq2 = tq2;
    }

    public String getQw1() {
        return qw1;
    }

    public void setQw1(String qw1) {
        this.qw1 = qw1;
    }

    public String getQw2() {
        return qw2;
    }

    public void setQw2(String qw2) {
        this.qw2 = qw2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
