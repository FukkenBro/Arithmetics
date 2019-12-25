package com.ChenP;

import java.util.Arrays;

public class Summator {

    private int[] a;
    private int[] b;
    private int periodIndex;
    private String negPrefix;

    public Summator(int[] fullA, int[] fullB, int periodIndex, String negPrefix) {
        this.a = fullA;
        this.b = fullB;
        this.periodIndex = periodIndex;
        this.negPrefix = negPrefix;
    }

    public String getNegPrefix() {
        return negPrefix;
    }

    public int[] getSum() {
        return mainLoop();
    }

    public String result() {
        return insertPeriod();
    }

    private int[] mainLoop() {
        int[] memory = new int[a.length];
        int[] result = new int[a.length];
        for (int i = result.length - 1, j = a.length - 1; j >= 0; j--, i--) {
            if (a[j] + b[j] + memory[i] >= 10) {
                result[i] += ((a[j] + b[j] + memory[i]) % 10);
                memory[i - 1]++;
            } else result[i] = a[j] + b[j] + memory[j];
        }
        return result;
    }


    private String insertPeriod() {
        return Arith.intArrToString(getNegPrefix(), mainLoop(), periodIndex);

    }

    @Override
    public String toString() {
        return "Summator{" +
                "a=" + Arrays.toString(a) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}
