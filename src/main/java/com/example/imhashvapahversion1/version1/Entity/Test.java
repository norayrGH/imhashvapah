package com.example.imhashvapahversion1.version1.Entity;

import javax.persistence.*;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "employee_name",unique = true , nullable = false)
    private String employeeName ;

    @Column(name = "employee_hch" ,unique = true , nullable = false)
    private Long hch ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getHch() {
        return hch;
    }

    public void setHch(Long hch) {
        this.hch = hch;
    }
}
