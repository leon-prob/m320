package ch.tbz.leon_michel.stock;

public class NewYorkStockExchange implements StockExchange {
    @Override
    public double getPrice(Share stockShare) {
        return stockShare.getValue() * 1.2;
    }
}
