package com.example.imhashvapahversion1.version1.Entity.partners.Customers;

public class Customer {
    private Long id ;
    private String customerType;
    private String  customerName;


    public Customer() {
    }

    public Customer(Long id, String customerType, String customerName) {
        this.id = id;
        this.customerType = customerType;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
