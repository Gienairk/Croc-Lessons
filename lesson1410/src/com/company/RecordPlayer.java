package com.company;

/**
 * Метод представляющий общие параметры и методы для музыкальных проигрывателей
 */
public class RecordPlayer {
    Carrier carrier;
    int keeCarrier;
    int currentSong;
    int maxSongs;
    boolean isItPlay=false;


    /**
     * Метод который пытается установить носитель данных в определенное устройство
     * при этом проверяются ключи обоих,
     * если ключи совпадают начинается воспроизведение и появляется уведомление,
     * иначе выводится оповещение о том что ключи не совпали.
     * Если во время работы подключить новое устройство то воспроизведление начнется с него ,
     * а старое хранилище будет автоматически отключено.
     * @param carrier устройство которое пытаемся подключить
     */
    public void setCarrier(Carrier carrier) {
        if (keeCarrier==carrier.getFormat()) {
            currentSong = 0;
            isItPlay=true;
            maxSongs=carrier.songs.size();
            System.out.println("Начато воспроизведение "+carrier.name);
            this.carrier=carrier;
        }else {
            System.out.println("Формат носителя не подходит проигрывателю");
        }
    }

    /**
     * Метод для отключения устройства хранения музыки от проигрывателя
     */
    public void outCarrier(){
        System.out.println("Воспроизведение остановленно "+carrier.name);
        isItPlay=false;

    }

    /**
     * Метод возвращающий данные о текущей песне
     */
    public void whatPlay(){
        if (isItPlay){
            System.out.println( carrier.getSong(currentSong).getInformation());
        } else {
            dontFindCarrier();
        }
    }

    /**
     * Метод переключения песни на следующую
     */
    public void nextSong(){
        if (isItPlay){
            if (currentSong<maxSongs-1){
                currentSong++;

            }else {
                currentSong=0;
            }
        }
        else {
            dontFindCarrier();
        }
    }
    /**
     * Метод переключения песни на прошлую
     */
    public void previousSong(){
        if (isItPlay){
            if (currentSong==0){
                currentSong=maxSongs-1;
            }else {
                currentSong--;
            }
        }else {
            dontFindCarrier();
        }
    }

    /**
     * Метод уведомляющий о том что устройство хранение не подключено
     */
    private void dontFindCarrier() {
        System.out.println("Воспроизведение еще не начато, выберите носитель");
    }
}
