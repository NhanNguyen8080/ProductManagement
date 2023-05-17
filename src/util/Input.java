/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author dell
 */
public class Input {
    private static Scanner sc = new Scanner(System.in);
    private static boolean cont = true;
    public static int getInteger(String inputMsg, String errorMsg) {
        int n;
        do {            
           try {
            System.out.println(inputMsg);
            n = Integer.parseInt(sc.nextLine());
            return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            } 
        } while (true);
    }
    public static int getInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;
        do {            
           try {
            System.out.println(inputMsg);
            n = Integer.parseInt(sc.nextLine());
            if (n < lowerBound || n > upperBound)
                throw new Exception();
            return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            } 
        } while (true);
    }
    
    public static String getString(String inputMsg, String errorMsg) {
        String s;
        do {            
            System.out.println(inputMsg);
            s = sc.nextLine().trim();
            if (s.isEmpty() || s.length() == 0)
                System.out.println(errorMsg);
            else
                return s;
        } while (true);
    }
    
    public static String getStringWithRegex(String inputMsg, String errorMsg, String regex) {
        String s;
        do {            
            System.out.println(inputMsg);
            s = sc.nextLine().trim();
            if (s.isEmpty() || s.length() == 0 || !s.matches(regex))
                System.out.println(errorMsg);
            else
                return s;
        } while (true);
    }
    
    public static double getDouble(String inputMsg, String errorMsg) {
        double n;
        do {            
           try {
            System.out.println(inputMsg);
            n = Integer.parseInt(sc.nextLine());
            return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            } 
        } while (true);
    }
    public static double getDouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n;
        do {            
           try {
            System.out.println(inputMsg);
            n = Integer.parseInt(sc.nextLine());
            if (n < lowerBound || n > upperBound)
                throw new Exception();
            return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            } 
        } while (true);
    }
}
