package com.company;

import java.util.ArrayList;

/**
 * Метод представляющий общие параметры и методы для хранилищ музыки
 */
public class Carrier {
    String name;
    ArrayList<Song> songs=new ArrayList<>();
    protected int format;

    /**
     * Метод добавляет произведение в хранилище
     * @param song данные песни
     */
    public void addSong(Song song){
        songs.add(song);
    }

    /**
     * Метод для удаления песни с данного носителя
     * если указаной песни не найдено , будет выведено уведомление
     * @param song данные о песне которую нужно удалить
     */
    public void removeSong(Song song){
        String name=song.getName();
        String duration=song.getDuration();
        String group=song.getGroup();
        for (int i = 0; i <songs.size() ; i++) {
            if (songs.get(i).getName().equals(name) &&
                songs.get(i).getDuration().equals(duration) &&
                songs.get(i).getGroup().equals(group) ){//getGroup()
                songs.remove(i);
                return;
            }
        }
        System.out.println("Данные о песне не найдены");
    }

    /**
     * Метод для получение песни по её номеру в хранилище
     * @param i номер песни
     * @return данные о песне
     */
    protected Song getSong(int i){
        return songs.get(i);
    }

    /**
     * Метод для получения формата хранилища
     * @return
     */
    protected int getFormat() {
        return format;
    }
}
