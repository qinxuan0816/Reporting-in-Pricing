/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportsInPricing;

/**
 *
 * @author serena
 */

import java.util.ArrayList;
import java.util.List;

public class MarketDirectory {
    private List<Market> markets;

    public MarketDirectory() {
        this.markets = new ArrayList<>();
    }

    public Market addMarket(String id, String name, String description) {
        Market m = new Market(id, name, description);
        markets.add(m);
        return m;
    }

    public List<Market> getMarkets() {
        return markets;
    }
}
