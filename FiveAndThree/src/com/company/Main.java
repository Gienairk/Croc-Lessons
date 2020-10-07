package com.company;

public class Main {

    public static void main(String[] args) {
    int firstDel=5;
    int secDel=3;
    String ans="";
        for (int i = 1; i <=100 ; i++) {
            if (i%firstDel==0)
                ans+="Fizz";
            if (i%secDel==0)
                ans+="Buss";
            if (ans.equals("")){
                System.out.println(i);
            }
            else {
                System.out.println(ans);
                ans="";
            }
        }
    }
}
