package vTrading.userManagement.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WatchList {

    private long id;
    private String name;
    private Set<String> tickers;

    public WatchList(long id, String name) {
        this.id = id;
        this.name = name;
        this.tickers = new HashSet<>();
    }

    public void addTicker(String ticker) {
        tickers.add(ticker);
    }

    public void addTickers(List<String> tickerList) {
        for (String ticker : tickerList) {
            tickers.add(ticker);
        }
    }

    public void removeTicker(String ticker) {
        tickers.remove(ticker);
    }

    public void clear() {
        tickers.clear();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getTickers() {
        return tickers;
    }

}
