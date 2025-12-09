/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportsInPricing;

/**
 *
 * @author serena
 */


public class Supplier {
    private String supplierId;
    private String name;
    private ProductCatalog productCatalog;

    public Supplier(String supplierId, String name) {
        this.supplierId = supplierId;
        this.name = name;
        this.productCatalog = new ProductCatalog();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }


    public String toString() {
        return name;
    }
}
