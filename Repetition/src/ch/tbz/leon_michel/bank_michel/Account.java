package ch.tbz.leon_michel.bank_michel;

public class Account {
    private final String username;
    private double credit;

    public Account(String username, double startCredit){
        this.username = username;
        credit = startCredit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getUsername() {
        return username;
    }
}
