package com.zwy.springmvc.config;

import java.util.Scanner;

/**
 * 独立化加密工具，加密后可去除
 */
public class PropertiesEncrypt {
    private static final String KEY="G0CvDz7oJn6";
    private static final String START_FILED ="ENC<" ;
    private static final String END_FILED =")";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String[] split = scanner.nextLine().split("=", 2);
            if(!split[1].startsWith(START_FILED)){
                continue;
            }
            String encryptString = EncryptUtil.encrypt(split[1],KEY);
            System.out.println(split[0]+"="+START_FILED+encryptString+END_FILED);
        }
    }
}
