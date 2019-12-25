package com.ChenP;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Subtracktor {

    private int[] a;
    private int[] b;
    private int periodIndex;
    private String negPrefix;

    public Subtracktor(int[] fullA, int[] fullB, int periodIndex) {
        this.a = fullA;
        this.b = fullB;
        this.periodIndex = periodIndex;
        if (isLarger()) {
            this.negPrefix = "+";
        }
        if (!isLarger()) {
            this.negPrefix = "-";
        }
    }

    public Subtracktor(int[] fullA, int[] fullB, int periodIndex, String prefixModificator) {
        this.a = fullA;
        this.b = fullB;
        this.periodIndex = periodIndex;
        if (prefixModificator == "+") {
            if (isLarger()) {
                this.negPrefix = "+";
            }
            if (!isLarger()) {
                this.negPrefix = "-";
            }
        }
        if (prefixModificator == "-") {
            if (isLarger()) {
                this.negPrefix = "-";
            }
            if (!isLarger()) {
                this.negPrefix = "+";
            }
        }
    }


    //valid
    public static int line() {
        //Отладка - Возвращает номер строки в которой метод был вызван
        return new Throwable().getStackTrace()[1].getLineNumber();
    }


    public int[] getSum() {
        return mainLoop();
    }

    public String result() {
        return Arith.intArrToString(negPrefix, mainLoop(), periodIndex);
    }

    private int[] mainLoop() {
        try {
            int[] minuend = a;
            int[] subtrahend = b;
            if (!isLarger()) {
                minuend = b;
                subtrahend = a;
            }
            int[] memory = new int[a.length];
            int[] result = new int[a.length];
            for (int i = result.length - 1, j = minuend.length - 1; j >= 0; j--, i--) {
                if (minuend[j] + memory[i] - subtrahend[j] < 0) {
                    memory[i] += 10;
                    result[i] = minuend[j] + memory[i] - subtrahend[j];
                    memory[i - 1] -= 1;
                } else result[i] = minuend[j] + memory[j] - subtrahend[j];
            }
            return result;
        } catch (Exception e) {
            System.out.println(line() + "sub mainLoop Error");
            return null;
        }
    }


    public boolean isLarger() {
        /**
         * Если a >= b - true.
         */
        int tmp = 0;
        for (int i = 0; i < a.length; ) {
            tmp = a[i] - b[i];
            if (tmp > 0) {
                return true;
            }
            if (tmp == 0) {
                i++;
                continue;
            }
            if (tmp < 0) {
                return false;
            }
        }
        System.out.println(line() + "isLarger Error");
        return false;
    }

    @Override
    public String toString() {
        return "Summator{" +
                "a=" + Arrays.toString(a) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}
