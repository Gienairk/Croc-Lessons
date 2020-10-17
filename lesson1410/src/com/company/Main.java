package com.company;

public class Main {

    public static void main(String[] args) {
	    Song first=new Song("The worst hangover ever","2:58","The offspring"); //несколько песен для тестов
        Song second=new Song("Psycho Killer","4:18","Talking Heads");
        Song third=new Song("The Pink Phantom","4:11","Gorillaz");
        Song fourth=new Song("Pac-Man","2:18","Gorillaz");

        VinylRecord vinylrecord=new VinylRecord("test");//
        vinylrecord.addSong(first);
        vinylrecord.addSong(second);
        vinylrecord.removeSong(first);

        VinylPlayers vinylPlayers=new VinylPlayers();
        vinylPlayers.setCarrier(vinylrecord);
        vinylPlayers.whatPlay();

        CompactDisc compactDisc=new CompactDisc("test2");
        compactDisc.addSong(third);
        compactDisc.addSong(fourth);

        CDPlayer cdPlayer=new CDPlayer();
        cdPlayer.setCarrier(compactDisc);

    }
}
