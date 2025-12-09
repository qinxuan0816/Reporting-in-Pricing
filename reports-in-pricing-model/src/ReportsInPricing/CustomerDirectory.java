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

public class CustomerDirectory {
    private List<Customer> customers;

    public CustomerDirectory() {
        this.customers = new ArrayList<>();
    }

    public Customer addCustomer(String id, String name, int age, double income, String region) {
        Customer c = new Customer(id, name, age, income, region);
        customers.add(c);
        return c;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}