package ch.tbz.leon_michel.inheritance;

public class Game extends Media{
    private int minimumAge;
    private double sizeInGB;
    @Override
    public void presentMedia() {
        System.out.println("*********************************************");
        System.out.println("              MEDIA LIBRARY GAME             ");
        System.out.println("*********************************************");
        System.out.println("Title:\t\t\t\t" + super.getTitle());
        System.out.println("Release Year:\t\t" + super.getReleaseYear());
        System.out.println("Software publisher:\t" + super.getCreator());
        System.out.println("Minimum Age:\t" + minimumAge);
        System.out.println("Size (in GB's)\t" + sizeInGB);
        System.out.println("Genre:\t\t\t\t" + super.getGenre());
        System.out.println("Language:\t\t\t" + super.getLanguage());
        System.out.println("Description:\t\t" + super.getDescription());
        System.out.println("*********************************************");
    }

    public int getMinimumAge(){return minimumAge;}
    public void setMinimumAge(int minimumAge){this.minimumAge = minimumAge;}

    public double getSizeInGB(){return sizeInGB;}
    public void setSizeInGB(double sizeInGB){this.sizeInGB = sizeInGB;}
}
