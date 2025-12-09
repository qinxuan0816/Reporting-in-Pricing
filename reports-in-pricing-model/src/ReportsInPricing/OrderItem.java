/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportsInPricing;

/**
 *
 * @author serena
 */


public class OrderItem {
    private Product product;
    private int quantity;
    private double salesPrice; // unit price actually sold

    public OrderItem(Product product, int quantity, double salesPrice) {
        this.product = product;
        this.quantity = quantity;
        this.salesPrice = salesPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public double getItemRevenue() {
        return salesPrice * quantity;
    }
}