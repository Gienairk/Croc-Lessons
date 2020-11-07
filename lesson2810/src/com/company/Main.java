package com.company;

public class Main {

    public static void main(String[] args) {
	 //Showtime x =new Showtime();
	 //Showtime y=new Showtime();
        //Создаются несколько людей для бронирования мест
        Thread x=new Thread(new Showtime(1));
        Thread y=new Thread(new Showtime(2));
        Thread z=new Thread(new Showtime(3));
        //Начинают случайно выбирать места и пытаться забронировать
        z.start();
        y.start();
	    x.start();



	 /*int count=0;
       for (Seat i :x.whatSeatIHave){
           System.out.print(count+" ");
           System.out.println(i.toString());
           count++;
       }
        System.out.println("-----------------------------------------------------------------------");
       count=0;
        for (Seat i :y.whatSeatIHave){
            System.out.print(count+" ");
            System.out.println(i.toString());
            count++;
        }*/

    }
}
