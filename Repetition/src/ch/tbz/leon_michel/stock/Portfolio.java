package ch.tbz.leon_michel.stock;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private final List<Share> shares;
    private final ZurichStockExchange zurichStockExchange;
    private final LondonStockExchange londonStockExchange;
    private final NewYorkStockExchange newYorkStockExchange;

    public Portfolio(ZurichStockExchange zurichStockExchange, LondonStockExchange londonStockExchange, NewYorkStockExchange newYorkStockExchange) {
        this.zurichStockExchange = zurichStockExchange;
        this.londonStockExchange = londonStockExchange;
        this.newYorkStockExchange = newYorkStockExchange;
        shares = new ArrayList<>();
    }

    public List<Share> getShares() {
        return shares;
    }

    public Share getShareByStockName(String stockName) throws Exception {
        for(Share share : shares) {
            if(share.getStockName().equals(stockName)){
                return share;
            }
        }
        throw new Exception("Not valid stock name!");
    }

    public double getStockPrice(String stockName, String stockExchangePlace) throws Exception {
        Share share = getShareByStockName(stockName);
        assert share != null;
        switch (stockExchangePlace) {
            case "zurich" -> {
                return zurichStockExchange.getPrice(share);
            }
            case "london" -> {
                return londonStockExchange.getPrice(share);
            }
            case "new york" -> {
                return newYorkStockExchange.getPrice(share);
            }
            default -> throw new Exception("Not valid stock exchange!");
        }
    }
}
