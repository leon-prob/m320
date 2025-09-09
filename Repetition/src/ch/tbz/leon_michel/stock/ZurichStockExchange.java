package ch.tbz.leon_michel.stock;

public class ZurichStockExchange implements StockExchange {

    @Override
    public double getPrice(Share stockShare) {
        return stockShare.getValue();
    }
}
