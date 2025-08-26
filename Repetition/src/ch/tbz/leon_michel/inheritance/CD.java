package ch.tbz.leon_michel.inheritance;

public class CD extends Media{
    private String album;
    private double lengthInMIN;
    @Override
    public void presentMedia() {
        System.out.println("*********************************************");
        System.out.println("               MEDIA LIBRARY CD              ");
        System.out.println("*********************************************");
        System.out.println("Title:\t\t\t\t" + super.getTitle());
        System.out.println("Release Year:\t\t" + super.getReleaseYear());
        System.out.println("Artist:\t\t\t\t" + super.getCreator());
        System.out.println("Album:\t\t\t\t" + getAlbum());
        System.out.println("Length (minutes):\t" + lengthInMIN);
        System.out.println("Genre:\t\t\t\t" + super.getGenre());
        System.out.println("Language:\t\t\t" + super.getLanguage());
        System.out.println("Description:\t\t" + super.getDescription());
        System.out.println("*********************************************");
    }

    public String getAlbum(){if(album != null){return album;} else{return "-";}}
    public void setAlbum(String album){this.album = album;}

    public double getLengthInMIN(){return lengthInMIN;}
    public void setLengthInMIN(double lengthInMIN){this.lengthInMIN = lengthInMIN;}
}