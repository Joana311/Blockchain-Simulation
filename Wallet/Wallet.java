package Wallet;

public class Wallet {

    private String name;
    private String hash;
    private Integer balance;

    public Wallet(String name, String hash, Integer balance) {
        this.name = name;
        this.hash = hash;
        this.balance = balance;
    }

    /*_______________________________________________________________*/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /*_______________________________________________________________*/
    @Override
    public String toString() {
        return "u: " + this.name
                + ", PK:" + this.hash
                + ", balance: " + this.balance;
    }

}
