package ch.tbz.leon_michel.stock;

public class Share {
    private double value;
    private final String stockName;

    public Share(String stockName) {
        this.stockName = stockName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStockName() {
        return stockName;
    }
}
