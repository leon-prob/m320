package ch.noseryoung;

class TwoPartJoke implements Joke {
    private final String setup;
    private final String delivery;
    private final String category;

    public TwoPartJoke(String setup, String delivery, String category) {
        this.setup = setup;
        this.delivery = delivery;
        this.category = category;
    }

    @Override
    public void display() {
        System.out.println("Category: " + category);
        System.out.println("-----------------");
        System.out.println(setup);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Joke delivery was interrupted!");
        }
        System.out.println("... " + delivery);
    }
}
