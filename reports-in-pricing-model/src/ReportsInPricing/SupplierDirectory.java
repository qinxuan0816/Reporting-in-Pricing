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

public class SupplierDirectory {
    private List<Supplier> suppliers;

    public SupplierDirectory() {
        this.suppliers = new ArrayList<>();
    }

    public Supplier addSupplier(String id, String name) {
        Supplier s = new Supplier(id, name);
        suppliers.add(s);
        return s;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
}
