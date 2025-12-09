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

public class Customer {
    private String customerId;
    private String name;
    private int age;
    private double income;
    private String region;

    private Market market;   // assigned market
    private Channel channel; // assigned channel

    private List<Order> orders;

    public Customer(String customerId, String name, int age, double income, String region) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.income = income;
        this.region = region;
        this.orders = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getIncome() {
        return income;
    }

    public String getRegion() {
        return region;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        if (!orders.contains(order)) {
            orders.add(order);
        }
    }


    public String toString() {
        return name + " (age=" + age + ", income=" + income + ")";
    }
}
