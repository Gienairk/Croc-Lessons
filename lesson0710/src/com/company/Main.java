package com.company;

public class Main {

    public static void main(String[] args) {
    int firstDel=5;
    int secDel=3;
        for (int i = 1; i <=100 ; i++) {
            if (i%firstDel==0 && i%secDel==0)
                System.out.println("FizzBuzz");
            else
            {
                if (i%firstDel==0)
                    System.out.println("Buzz");
                else {
                    if (i%secDel==0)
                        System.out.println("Fizz");
                    else
                        System.out.println(i);
                }
            }
        }

    }

}
