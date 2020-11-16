package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //System.out.println("Введите  путь");
        //String path = in.nextLine();
        String path = "./КРОК/task_6_2/src/././../../task_6_1/../../../мемы/котики/./";

        List<String> longPath=new LinkedList<String>();
        Collections.addAll(longPath,path.split("/"));
        longPath.removeIf(x->x.equals("."));//1 способ удаления "." читабельнее, но приходится обходить коллекцию 2 раза в силу реализации =>дольше на больший коллекциях
        List<String> shortPath=new LinkedList<>();
        int namelevel=0;
        boolean firstToTop;
        while (longPath.size()!=0){
/*
            //2 способ удаления "." быстрее тк коллекция обходится только в цикле=>(на малых быстрее), но не так удобно
           if (longPath.get(0).equals(".")){
                longPath.remove(0);
                continue;
            }*/
            firstToTop=longPath.get(0).equals("..");
            if (firstToTop && namelevel==0) {
                shortPath.add(longPath.get(0));
                longPath.remove(0);
            }else{
                if (!firstToTop){
                    namelevel++;
                    shortPath.add(longPath.get(0));
                    longPath.remove(0);
                }else{
                    if (firstToTop && namelevel!=0){
                        namelevel--;
                        shortPath.remove(shortPath.size()-1);
                        longPath.remove(0);
                    }
                }
            }
        }
        printWithoutSlash(shortPath);

    }

    public static void print(Collection<String>x ){
        for ( String i:x) {
            System.out.print(i+"/");
        }
        System.out.println();
    }

    public static void printWithoutSlash(List<String>x ){
        if( x.size()!=0){
            int i;
            for ( i = 0; i <x.size()-1 ; i++) {
                System.out.print(x.get(i)+"/");
            }
            System.out.println(x.get(i));
        } else
            System.out.println("./");
    }
}
