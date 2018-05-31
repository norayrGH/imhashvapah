package com.example.imhashvapahversion1.version1.Entity.partners.suppliers;

import java.util.Date;

public class Service {
    private Long id ;
    private String serviceType;
    private String serviceName;
    private Date contractDate;
    private String contractNumber;
    private Long supplierId ;
    private String supplierType;

    public Service() {

    }

    public Service(Long id, String serviceType, String serviceName, Date contractDate, String contractNumber, Long supplierId, String supplierType) {
        this.id = id;
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.contractDate = contractDate;
        this.contractNumber = contractNumber;
        this.supplierId = supplierId;
        this.supplierType = supplierType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
}
