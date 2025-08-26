package ch.tbz.leon_michel.inheritance;

public class Media {
    private String title;
    private int releaseYear;
    private String creator;
    private String genre;
    private String language;
    private String description;


    public void presentMedia() {
        System.out.println("*********************************************");
        System.out.println("              MEDIA LIBRARY ITEM             ");
        System.out.println("*********************************************");
        System.out.println("Title:\t" + title);
        System.out.println("Release Year:\t" + releaseYear);
        System.out.println("Creator:\t" + creator);
        System.out.println("Genre:\t" + genre);
        System.out.println("Language:\t" + language);
        System.out.println("Description:\t" + description);
        System.out.println("*********************************************");
    }
    public String getTitle(){if(title != null){return title;} else{return "-";}}
    public void setTitle(String title){this.title = title;}

    public int getReleaseYear(){return releaseYear;}
    public void setReleaseYear(int releaseYear){this.releaseYear = releaseYear;}

    public String getCreator(){if(creator != null){return creator;} else{return "-";}}
    public void setCreator(String creator){this.creator = creator;}

    public String getGenre(){if(genre != null){return genre;} else{return "-";}}
    public void setGenre(String genre){this.genre = genre;}

    public String getLanguage(){if(language != null){return language;} else{return "-";}}
    public void setLanguage(String language){this.language = language;}

    public String getDescription(){if(description != null){return description;} else{return "-";}}
    public void setDescription(String description){this.description = description;}
}

