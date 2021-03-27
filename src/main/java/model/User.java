package model;

public class User {
    private String password;
    private String name;
    private int type;

    /**
     * constructorul clasei Client
     * */
    public User(String name, int type, String password) {
        super();
        this.type=type;
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
