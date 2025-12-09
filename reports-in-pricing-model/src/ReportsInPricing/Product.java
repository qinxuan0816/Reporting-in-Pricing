/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportsInPricing;

/**
 *
 * @author serena
 */

public class Product {
    private String productId;
    private String name;
    private double basePrice;
    private Supplier supplier;

    public Product(String productId, String name, double basePrice, Supplier supplier) {
        this.productId = productId;
        this.name = name;
        this.basePrice = basePrice;
        this.supplier = supplier;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    @Override
    public String toString() {
        return name + " $" + basePrice;
    }
}