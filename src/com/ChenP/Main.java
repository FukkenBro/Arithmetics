package com.ChenP;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        String a = "99";
//        String b = "100.132";
//        double q = Double.parseDouble(a);
//        double p = Double.parseDouble(b);
//
//        System.out.println("custom method  "+Arithmetic.add(a,b));
//        double sum = q+p;
//        System.out.println("default method "+sum);


        String a = "0.0545641656541635464168454616523462454";
        String b = "-100";
        //test loop
        for (int i = 0; i <= 16; i++) {
            if (i == 0) {
                a = "31.31";
                b = "1.1";
                call(a, b);
            }
            if (i == 1) {
                a = "98.99";
                b = "1.019";
                call(a, b);
            }
            if (i == 2) {
                a = "3.31";
                b = "198.1";
                call(a, b);
            }
            if (i == 3) {
                a = "3.31";
                b = "198.1";
                call(a, b);
            }
            if (i == 4) {
                a = "3.31";
                b = "198.14455";
                call(a, b);
            }
            if (i == 5) {
                a = "0.0";
                b = "198.1";
                call(a, b);
            }
            if (i == 6) {
                a = "0";
                b = "198.1";
                call(a, b);
            }
            if (i == 7) {
                a = "00000000000.0000000000000000";
                b = "198.1";
                call(a, b);
            }
            if (i == 8) {
                a = "-31.31";
                b = "-1.1";
                call(a, b);
            }
            if (i == 9) {
                a = "-98.99";
                b = "-1.019";
                call(a, b);
            }
            if (i == 10) {
                a = "-3.31";
                b = "-198.1";
                call(a, b);
            }
            if (i == 11) {
                a = "-3.31";
                b = "-198.1";
                call(a, b);
            }
            if (i == 12) {
                a = "-3.31";
                b = "-198.14455";
                call(a, b);
            }
            if (i == 13) {
                a = "-0.0";
                b = "-198.1";
                call(a, b);
            }
            if (i == 14) {
                a = "-0";
                b = "-198.1";
                call(a, b);
            }
            if (i == 15) {
                a = "-00000000000.0000000000000000";
                b = "-198.1";
                call(a, b);
            }
            if (i == 16) {
                a = "123141254254426114461521412462124.352134123514123341253141224511421";
                b = "12314123314122311233412231133413221412443.251421435124314";
                call(a, b);
            }
        }


    }

    public static void call(String a, String b) {
//        System.out.println(Arith.add(a,b));
//        System.out.println(Double.parseDouble(a)+Double.parseDouble(b));
        ///////////////////////////////////////////////////////////////////////////
        System.out.println("               " + a);
        System.out.println("               " + b);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //custom method
        System.out.println(Arith.add(a, b));
        //default method
        System.out.println(Double.parseDouble(a) + Double.parseDouble(b));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("--------------------------------------------------------------");
        //custom method
        System.out.println(Arith.sub(a, b));
        //default method
        System.out.println(Double.parseDouble(a) - Double.parseDouble(b));
        System.out.println("--------------------------------------------------------------");
    }

}


