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

public class Market {
    private String marketId;
    private String name;
    private String description;
    private List<Channel> channels;
    private List<Customer> customers;

    public Market(String marketId, String name, String description) {
        this.marketId = marketId;
        this.name = name;
        this.description = description;
        this.channels = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public String getMarketId() {
        return marketId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addChannel(Channel channel) {
        if (!channels.contains(channel) && channels.size() < 2) {
            channels.add(channel);
        }
    }

    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }

 
    public String toString() {
        return name;
    }
}
