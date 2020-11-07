package com.company;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс представления мест
 */
public class Seat {
    // номер ряда
    private final int row;
    // номер места
    private final int seat;

    /**
     * Стандартный конструктор
     * @param row номер ряда
     * @param seat номер места
     */
    public Seat(int row, int seat) {
        this.row = row;
        this.seat = seat;
    }

    /**
     * конструктор по умолчанию, расчитан на зал 10 на 10
     * выбирает случайное место из него
     */
    public Seat(){
        //this.row= (int) (1+Math.random()*10);
        //this.seat=(int) (1+Math.random()*10);
        this.row= ThreadLocalRandom.current().nextInt(1,10);
        this.seat= ThreadLocalRandom.current().nextInt(1,10);
    }

    /**
     * Возврат строкового представления класса
     */
    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", seat=" + seat +
                '}';
    }

    /**
     *переопределен метот equals для того что бы метод contains() выполнялся корректно
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seat)) return false;
        Seat seat1 = (Seat) o;
        return row == seat1.row &&
                seat == seat1.seat;
    }

    /**
     *переопределен метот equals для того что бы метод contains() выполнялся корректно
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, seat);
    }
}

