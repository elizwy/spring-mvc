package com.zwy.springmvc.config;

import java.util.Scanner;

public class PropertiesEncrypt {
    private static final String KEY="G0CvDz7oJn6";
    private static final String START_FILED ="ENC(" ;
    private static final String END_FILED =")";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String[] split = scanner.nextLine().split("=", 2);
            if(split[1].startsWith("ENC(")){
                continue;
            }
            String encryptString = null;
            try {
                encryptString = EncryptUtil.encrypt(split[1],KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(split[0]+"="+START_FILED+encryptString+END_FILED);
        }
    }
}
