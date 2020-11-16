package com.zym.leetcode.daycode;

public class Multiply {
    public static void main(String[] args) {
        String multiply = multiply("6913259244", "71103343");
        System.out.println(multiply);
//        long maxValue = Long.MAX_VALUE;
//        System.out.println(maxValue);
        // long maxValue
        // 9223372036854775807
        // 491555843274052692
        // "419254329864656431168468"

//        long i1 = 6913259244L;
//        long i2 = 71103343;
//        System.out.println(i1 * i2);

    }

    public static String multiply(String num1, String num2) {
        if("0".equalsIgnoreCase(num1) || "0".equalsIgnoreCase(num2)) {
            return "0";
        }
        char[] charArrOne = num1.toCharArray();
        char[] charArrTwo = num2.toCharArray();
        long calculate = calculate(charArrOne);
        long calculate1 = calculate(charArrTwo);
        return calculate * calculate1 + "";
    }
    public static long calculate(char[] charArr) {
        int loopLength = charArr.length;
        long transFormVal = 0 ;
        long times = 1L;
        for(int i = loopLength -1 ; i >= 0 ; i--) {
            times = 1;
            for(int j = 0 ; j < i ;j++) {
                times*=10;
            }
            transFormVal+=times * Integer.parseInt(charArr[loopLength -i -1]+"") ;
        }
        return transFormVal;
    }
}
