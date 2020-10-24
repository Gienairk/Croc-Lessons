package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите полный или локальный путь");
        String path = in.nextLine();
        //String path = "test2.txt";
        Countingwords(path);
    }

    public static void Countingwords(String path) {
        try {
            File file = new File(path);
            Scanner in = new Scanner(file);
            int count = 0;
            String[] str ;
            String temp ;
            while (in.hasNextLine()) {
                temp = in.nextLine();
                temp = temp.trim();
                //temp=temp.replaceAll("[^А-Яа-я0-9] ", " ");
                temp = temp.replaceAll("[,.!?-]", " ");
                str = temp.replaceAll(" +", " ").split(" ");
                count += str.length;

            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            System.out.println(new IllegalArgumentException("По данному пути файл не найден"));
        }

    }
}
