package model;

public class Transfer {

    private int id;
    private String idDestinationAccount;
    private String idSourceAccount;
    private float money;

    public Transfer(int id,String idD, String idS, float money){
        this.idDestinationAccount=idD;
        this.idSourceAccount=idS;
        this.id=id;
        this.money=money;

    }

    public int getId() {
        return id;
    }

    public String getIdDestinationAccount() {
        return idDestinationAccount;
    }

    public String getIdSourceAccount() {
        return idSourceAccount;
    }

    public float getMoney() {
        return money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdDestinationAccount(String idDestinationAccount) {
        this.idDestinationAccount = idDestinationAccount;
    }

    public void setIdSourceAccount(String idSourceAccount) {
        this.idSourceAccount = idSourceAccount;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
