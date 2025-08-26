package ch.tbz.leon_michel.inheritance;

public class Movie extends Media{
    private int minimumAge;
    private int lengthInMIN;
    @Override
    public void presentMedia() {
        System.out.println("*********************************************");
        System.out.println("              MEDIA LIBRARY MOVIE            ");
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println("Title:\t\t\t\t" + super.getTitle());
        System.out.println("Release Year:\t\t" + super.getReleaseYear());
        System.out.println("Regisseur:\t\t\t\t" + super.getCreator());
        System.out.println("Minimum Age:\t" + minimumAge);
        System.out.println("Length (minutes):\t" + lengthInMIN);
        System.out.println("Genre:\t\t\t\t" + super.getGenre());
        System.out.println("Language:\t\t\t" + super.getLanguage());
        System.out.println("Description:\t\t" + super.getDescription());
        System.out.println("*********************************************");
    }

    public int getMinimumAge(){return minimumAge;}
    public void setMinimumAge(int minimumAge){this.minimumAge = minimumAge;}

    public int getLengthInMIN(){return lengthInMIN;}
    public void setLengthInMIN(int lengthInMIN){this.lengthInMIN = lengthInMIN;}

}
