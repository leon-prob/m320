package ch.tbz.leon_michel.inheritance;

public class Book extends Media{
    private String publisher;
    private int amountOfPages;

    @Override
    public void presentMedia() {
        System.out.println("*********************************************");
        System.out.println("              MEDIA LIBRARY BOOK             ");
        System.out.println("*********************************************");
        System.out.println("Title:\t\t\t\t" + super.getTitle());
        System.out.println("Release Year:\t\t" + super.getReleaseYear());
        System.out.println("Author:\t\t\t\t" + super.getCreator());
        System.out.println("Publisher:\t\t\t" + getPublisher());
        System.out.println("Length (Pages):\t\t" + amountOfPages);
        System.out.println("Genre:\t\t\t\t" + super.getGenre());
        System.out.println("Language:\t\t\t" + super.getLanguage());
        System.out.println("Description:\t\t" + super.getDescription());
        System.out.println("*********************************************");
    }

    public String getPublisher(){if(publisher != null){return publisher;} else{return "-";}}
    public void setPublisher(String publisher){this.publisher = publisher;}

    public int getAmountOfPages(){return amountOfPages;}
    public void setAmountOfPages(int amountOfPages){this.amountOfPages = amountOfPages;}
}

