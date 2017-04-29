package com.example.biguncler.launcher.util;

/**
 * Created by Biguncler on 19/01/2017.
 */

public class ChineseNumber {
    public static final String zero="零";
    public static final String one="一";
    public static final String two="二";
    public static final String three="三";
    public static final String four="四";
    public static final String five="五";
    public static final String six="六";
    public static final String seven="七";
    public static final String eight="八";
    public static final String nine="九";
    public static final String ten="十";

    /**
     * 只适用到40
     * @param number
     * @return
     */
    public static String getChineseNumber(int number){
        if(number<11&&number>-1){
            return getSingleChineseNumber(number);
        }else if(number<20){
           number=number-10;
            return ten+getSingleChineseNumber(number);
        }else if(number<30){
            number=number-20;
            if(number==0){
                return two+ten;
            }else{
                return two+ten+getSingleChineseNumber(number);
            }

        }else if(number<40){
            number=number-30;
            if(number==0){
                return three+ten;
            }else{
                return three+ten+getSingleChineseNumber(number);
            }
        }else{
            return zero;
        }
    }


    private static String getSingleChineseNumber(int number){

        if(number==0){
            return zero;
        }else  if(number==1){
            return one;
        }else  if(number==2){
            return two;
        }else  if(number==3){
            return three;
        }else  if(number==4){
            return four;
        }else  if(number==5){
            return five;
        }else  if(number==6){
            return six;
        }else  if(number==7){
            return seven;
        }else  if(number==8){
            return eight;
        }else  if(number==9){
            return nine;
        }else  if(number==10){
            return ten;
        }else{
            return zero;
        }

    }

}
