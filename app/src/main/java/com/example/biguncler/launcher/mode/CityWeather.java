package com.example.biguncler.launcher.mode;

import java.util.List;

/**
 * Created by Biguncler on 11/03/2017.
 */

public class CityWeather {
    /*"data": {
        "cityId": "CH280601",
                "cityName": "深圳",
                "sj": "2017-03-11 11:00:00",
                "list": [
        {
            "tq1": "小雨",
                "numtq1": "07",
                "numtq2": "07",
                "tq2": "小雨",
                "qw1": "21",
                "qw2": "18",
                "fl1": "微风",
                "fl2": "3-4级",
                "numfl1": "0",
                "numfl2": "1",
                "fx1": "无持续风向",
                "fx2": "东风",
                "numfx1": "0",
                "numfx2": "2",
                "date": "2017-03-11"
        },
        {
            "tq1": "阴",
                "numtq1": "02",
                "numtq2": "02",
                "tq2": "阴",
                "qw1": "23",
                "qw2": "19",
                "fl1": "微风",
                "fl2": "微风",
                "numfl1": "0",
                "numfl2": "0",
                "fx1": "无持续风向",
                "fx2": "无持续风向",
                "numfx1": "0",
                "numfx2": "0",
                "date": "2017-03-12"
        }
        ]
    }*/

    private String cityName;
    private List<Weather> list;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public List<Weather> getList() {
        return list;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }
}
