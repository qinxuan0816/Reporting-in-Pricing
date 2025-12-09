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

public class Order {
    private String orderId;
    private Customer customer;
    private Channel channel;
    private List<OrderItem> orderItems;

    public Order(String orderId, Customer customer, Channel channel) {
        this.orderId = orderId;
        this.customer = customer;
        this.channel = channel;
        this.orderItems = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Channel getChannel() {
        return channel;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getItemRevenue();
        }
        return total;
    }
}
