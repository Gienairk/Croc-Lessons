package com.company;

/**
 * Класс представляющий данные песни и служащий для хранения и последующего получения данных о ней.
 */
public class Song {
     private String name;
     private String duration;
     private String group;

    /**
     * Конструктор по умолчанию
     * @param name Название песни
     * @param duration Длительность песни
     * @param group Название группы записавшей песню
     */
    public Song(String name, String duration, String group) {
        this.name = name;
        this.duration = duration;
        this.group = group;
    }

    /**
     * Метод служащий для получения данных о песне
     * @return Строковое представление всех данных песни
     */
    public String getInformation(){
        return "name: "+getName() +" \nduration: " +getDuration() +" \ngroup: "+ getGroup()+"\n";
    }

    /**
     * Метод служащий для получения названия песни
     */
    protected String getName() {
        return name;
    }
    /**
     * Метод служащий для получения длительности песни
     */
    protected String getDuration() {
        return duration;
    }
    /**
     * Метод служащий для название группы исполнителя песни
     */
    protected String getGroup() {
        return group;
    }
}
