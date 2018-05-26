package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses;

public class Debt {
    private Long id ;
private String name;
private int prepayment;
private int debt;
private String type;

    public Debt() {
    }

    public Debt(Long id, String name, int prepayment, int debt, String type) {
        this.id = id;
        this.name = name;
        this.prepayment = prepayment;
        this.debt = debt;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(int prepayment) {
        this.prepayment = prepayment;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
