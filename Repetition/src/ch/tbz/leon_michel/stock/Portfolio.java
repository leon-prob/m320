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

    public double getStockPrice(String stockName,
                                StockExchange stockExchange) throws Exception {
        Share share = getShareByStockName(stockName);
        assert share != null;
        return stockExchange.getPrice(share);
    }
}
