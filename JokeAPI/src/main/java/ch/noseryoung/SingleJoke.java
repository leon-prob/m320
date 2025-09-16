package ch.noseryoung;

class SingleJoke implements Joke {
    private final String text;
    private final String category;

    public SingleJoke(String text, String category) {
        this.text = text;
        this.category = category;
    }

    @Override
    public void display() {
        System.out.println("Category: " + category);
        System.out.println("-----------------");
        System.out.println(text);
    }
}
