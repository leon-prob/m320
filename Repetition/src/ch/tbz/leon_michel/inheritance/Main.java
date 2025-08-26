package ch.tbz.leon_michel.inheritance;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CD cd = new CD();
        cd.setTitle("bot");
        cd.setAlbum("Album");
        cd.setLengthInMIN(420.69);
        cd.setCreator("The Creator");
        cd.setGenre("Music");
        cd.setLanguage("czech");
        cd.setReleaseYear(2999);
        cd.setDescription("The czech album");
        Book book = new Book();
        book.setTitle("Gödel Escher Bach");
        book.setCreator("Douglas R. Hofstadter");
        book.setReleaseYear(1979);


        ArrayList<Media> collection = new ArrayList<>();
        collection.add(cd);
        collection.add(book);


    }
}