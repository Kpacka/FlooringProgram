/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.view;

import java.util.Scanner;

/**
 *
 * @author jenniferbliss
 */
public class UserIoImpl implements UserIo {

    @Override
    public void print(String message) {
        
        
        System.out.println(message);
    }

 
    public double readDouble(String prompt) {

        boolean again = false;
        String userInput;
        int dot;
        do {
            dot = 1;
            again = false;

            userInput = readString(prompt);
            char[] userCheck = userInput.toCharArray();
            if (userCheck.length == 0) {
                again = true;
            } else {
                for (char s : userCheck) {

                    if (s == '.') {

                        if (dot != 1) {
                            again = true;
                        }
                        dot--;
                    } else if (!Character.isDigit(s)) {
                        again = true;

                    } else {

                    }

                }

            }
        } while (again);
        return Double.parseDouble(userInput);
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean again;
        double input;
        do {

            again = true;

            input = readDouble(prompt);

            if (input > min && input < max) {
                again = false;
            }
        } while (again);
        return input;

    }

    @Override
    public float readFloat(String prompt) {

        boolean again = false;
        String userInput;
        int dot;
        do {
            dot = 1;
            again = false;

            userInput = readString(prompt);
            char[] userCheck = userInput.toCharArray();
            if (userCheck.length == 0) {
                again = true;
            } else {
                for (char s : userCheck) {

                    if (s == '.') {

                        if (dot != 1) {
                            again = true;
                        }
                        dot--;
                    } else if (!Character.isDigit(s)) {
                        again = true;

                    } else {

                    }

                }

            }
        } while (again);
        return Float.parseFloat(userInput);
    }

  
    public float readFloat(String prompt, float min, float max) {
        boolean again;
        float input;
        do {

            again = true;

            input = readFloat(prompt);

            if (input > min && input < max) {
                again = false;
            }
        } while (again);
        return input;

    }

    @Override
    public int readInt(String prompt) {

        boolean again = false;
        String userInput;

        do {

            again = false;

            userInput = readString(prompt);
            char[] userCheck = userInput.toCharArray();
            if (userCheck.length == 0) {
                again = true;
            } else {
                for (char s : userCheck) {

                    if (s == '.') {

                        again = true;

                    } else if (!Character.isDigit(s)) {

                        again = true;

                    } else {

                    }

                }

            }
        } while (again);
        return Integer.parseInt(userInput);
    }

 
    @Override
    public int readInt(String prompt, int min, int max) {
        boolean again;
        int input;
        do {

            again = true;

            input = readInt(prompt);

            if (input >= min && input <= max) {
                again = false;
            }
        } while (again);
        return input;

    }

    @Override
    public long readLong(String prompt) {

        boolean again = false;
        String userInput;

        do {

            again = false;

            userInput = readString(prompt);
            char[] userCheck = userInput.toCharArray();
            if (userCheck.length == 0) {
                again = true;
            } else {
                for (char s : userCheck) {

                    if (s == '.') {

                        again = true;

                    } else if (!Character.isDigit(s)) {
                        again = true;

                    } else {

                    }

                }

            }
        } while (again);
        return Long.parseLong(userInput);
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean again;
        long input;
        do {

            again = true;

            input = readLong(prompt);

            if (input > min && input < max) {
                again = false;
            }
        } while (again);
        return input;

    }

   @Override
    public String readString(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        return sc.nextLine();
    }

}

