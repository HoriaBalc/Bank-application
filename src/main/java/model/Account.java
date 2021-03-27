package model;

import java.sql.Date;

public class Account {

    private int id;
    private String type;
    private float money;
    private java.sql.Date dateCreation;
    private String cnp;

    public Account(int id, String cnp, String type, float money, java.sql.Date dateCreation){
        this.id=id;
        this.money=money;
        this.dateCreation=dateCreation;
        this.type=type;
        this.cnp=cnp;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public float getMoney() {
        return money;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
