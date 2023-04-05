package com.daclink.ftocconverter;

public class Util {
    public static double f_to_c(double fahrenheit){
        double celsius = 0.0;
        celsius = (fahrenheit-32)*(5/9.0);
        return celsius;
    }

    public static double c_to_f(double celsius){
        double fahrenheit = 0.0;
        fahrenheit = (celsius*(9/5.0)+32);
        return fahrenheit;
    }
}
