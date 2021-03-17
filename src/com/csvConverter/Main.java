package com.csvConverter;


public class Main {
    public static String path = "csv/input.csv";
    private static final Converter converter = new Converter();

    public static void main(String[] args)  {
        converter.convert(path);
    }
}
