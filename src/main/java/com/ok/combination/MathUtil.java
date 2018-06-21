package com.ok.combination;

class MathUtil {

    private MathUtil() {

    }

    static double fact(double n) {
        double res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
