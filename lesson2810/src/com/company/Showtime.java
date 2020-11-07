package com.company;

import java.util.HashSet;

import java.util.concurrent.ThreadLocalRandom;

/**
 * метод для паралельного бронирования мест
 */
public class  Showtime implements Runnable  {
    /**поле для фиксации synchronized */
    private final static  Object lock=new Object();
    /** переменная отвечающая за то сколько свободных мест может остаться в зале */
    private  boolean haveSeats=true;
    /** набор свободных мест, общий для всех потоков*/
    private  static HashSet<Seat> freeseats=Startseats();
    /** Набор мест уникальный для каждого потока*/
    private  HashSet<Seat> whatSeatIHave=new HashSet<>();
    /** числовой идентифика́тор потока */
    private  int numb;

    /**
     * конструктор класса
     * @param x числовой идентифика́тор потока
     */
    public Showtime(int x){
        this.numb=x;
    }

    /**
     * Начальное заполнения зала пустыми местами
     * @return набор представляющий собой зал в котором все места свободны
     */
    private static HashSet<Seat> Startseats(){
        HashSet<Seat> freeseats = new HashSet<>();
        for (int i = 1; i <11 ; i++) {
            for (int j = 1; j < 11; j++) {
                freeseats.add(new Seat(i,j));
            }
        }
        return freeseats;
    }

    /**
     * получение свободный мест на текущий момент
     * @return набор свободных мест
     */
    public HashSet<Seat> getFreeSeats() {
        return freeseats;
    }


    // бронирует место на текущий сеанс;
    // возвращает true, если место успешно забронировано
    // или false, если бронирование не удалось
    // (кто-то успел раньше)

    /**
     * Бронирование переданного места и запись для того что бы определить какой поток забронировал его
     * либо указывает на невозможность бронирования
     * @param seat место для бронирования
     * @return успешно ли прошло бронирование
     */
    public boolean bookSeat(Seat seat) {

        if (getFreeSeats().size()<21){// указывает сколько мест может быть свободным на конец бронирования
            haveSeats=false;// пока свободных мест больше , потоки не останавливаются, чем меньше число тем дольше будет работать программа
        }
       // freeseats.add(seat);
        //System.out.println(freeseats.contains(seat));
        if (freeseats.contains(seat)){
            System.out.println("поток "+ numb+" смог забронировать место "+ seat);
            //System.out.println(getFreeSeats().size());
            freeseats.remove(seat);
            whatSeatIHave.add(seat);
            return true;
        }else{
            System.out.println("поток "+ numb+" не смог забронировать место "+ seat);
            return false;}
    }

    /**
     * запуск потоков со случайной генерацией мест  и последующий вывод мест забронированных потоком
     */
    @Override
    public void run() {
        while (haveSeats){
            synchronized (lock){
                int x= ThreadLocalRandom.current().nextInt(1,10);
                int y= ThreadLocalRandom.current().nextInt(1,10);
                bookSeat(new Seat(x,y));
            }
        }
        int count=1;
        for (Seat i :whatSeatIHave){
            System.out.println("поток "+numb+" "+ count +" "+i.toString());
            count++;
        }

        System.out.println("поток закончен "+numb);
    }
}
