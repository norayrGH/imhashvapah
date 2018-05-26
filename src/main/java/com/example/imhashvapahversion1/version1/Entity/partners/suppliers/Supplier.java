package com.example.imhashvapahversion1.version1.Entity.partners.suppliers;

public class Supplier {
    private Long id ;
    private String supplierType;
    private String supplierName;

    public Supplier( ) {

    }

    public Supplier(Long id, String supplierType, String supplierName) {
        this.id = id;
        this.supplierType = supplierType;
        this.supplierName = supplierName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
