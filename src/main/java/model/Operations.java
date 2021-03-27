package model;

public class Operations {
    private int id;
    private String name;
    private String account;
    private String client;
    private String user;

    public Operations(int id, String name, String cau){

        this.id=id;
        this.name=name;
        if(name.contains("client"))
            this.client=cau;
        if(name.contains("account"))
            this.account=cau;
        if(name.contains("user"))
            this.user=cau;

    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getClient() {
        return client;
    }

    public String getUser() {
        return user;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
