package Wallet;

public class Wallet {

    private String name;
    private String publicKey;
    private Integer balance;

    public Wallet(String name, String publicKey, Integer balance) {
        this.name = name;
        this.publicKey = publicKey;
        this.balance = balance;
    }

    /*_______________________________________________________________*/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String hash) {
        this.publicKey = hash;
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
                + ", PK:" + this.publicKey
                + ", balance: " + this.balance;
    }

}
