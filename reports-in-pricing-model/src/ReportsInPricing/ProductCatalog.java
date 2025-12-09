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

public class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public Product addProduct(String id, String name, double basePrice, Supplier supplier) {
        Product p = new Product(id, name, basePrice, supplier);
        products.add(p);
        return p;
    }

    public List<Product> getProducts() {
        return products;
    }
}
