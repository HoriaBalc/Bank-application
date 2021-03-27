package model;
/**
 * 		Aceasta clasa contine doua variabile private care reprezinta coloanele tabelului Client.
 * */
public class Client {
    private String personalNumericCode;
    private String name;
    private String identificationNumber;
    private String address;
    private String oras;

    /**
     * constructorul clasei Client
     * */
    public Client(String name,String personalNumericCode, String address, String oras, String identificationNumber) {
        super();
        this.personalNumericCode=personalNumericCode;
        this.address=address;
        this.identificationNumber=identificationNumber;
        this.name = name;
        this.oras = oras;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getPersonalNumericCode() {
        return personalNumericCode;
    }



    public String getAddress() {
        return address;
    }

    public void setPersonalNumericCode(String personalNumericCode) {
        this.personalNumericCode = personalNumericCode;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter pentru variabila name
     * */

    public String getName() {
        return name;
    }

    /**
     * Setter pentru variabila name
     * */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter pentru variabila oras
     * */
    public String getOras() {
        return oras;
    }

    /**
     * setter pentru variabila oras
     * */
    public void setOras(String oras) {
        this.oras = oras;
    }

    /**
     * Metoda toString are rolul de a afisa datele din  aceasta clasa.
     */
    @Override
    public String toString() {
        return "Client{" +
                "personalNumericCode='" + personalNumericCode + '\'' +
                ", name='" + name + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", address='" + address + '\'' +
                ", oras='" + oras + '\'' +
                '}';
    }
}
