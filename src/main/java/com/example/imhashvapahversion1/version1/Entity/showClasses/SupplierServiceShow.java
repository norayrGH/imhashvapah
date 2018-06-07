package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class SupplierServiceShow {

    Long id;
    String serviceName;
    Date startDate;
    String serviceCost;
    String supplierName;
    String serviceType;
    String pereodicOrNot;

    public SupplierServiceShow() {
    }

    public SupplierServiceShow(Long id, String serviceName, Date startDate, String serviceCost, String supplierName, String serviceType, String pereodicOrNot) {
        this.id = id;
        this.serviceName = serviceName;
        this.startDate = startDate;
        this.serviceCost = serviceCost;
        this.supplierName = supplierName;
        this.serviceType = serviceType;
        this.pereodicOrNot = pereodicOrNot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(String serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getPereodicOrNot() {
        return pereodicOrNot;
    }

    public void setPereodicOrNot(String pereodicOrNot) {
        this.pereodicOrNot = pereodicOrNot;
    }
}
