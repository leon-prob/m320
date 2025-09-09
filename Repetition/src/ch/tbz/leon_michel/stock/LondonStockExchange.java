package ch.tbz.leon_michel.stock;

public class LondonStockExchange implements StockExchange {
    @Override
    public double getPrice(Share stockShare) {
        return stockShare.getValue() * 0.8;
    }
}
