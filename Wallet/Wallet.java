package Wallet;

/**
 * This class represents a Wallet object.
 *
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class Wallet {

    private String name;
    private String publicKey;
    private Integer balance;

    /**
     * Constructs a Wallet object with the specified name, public key, and
     * balance.
     *
     * @param name the name of the wallet
     * @param publicKey the public key of the wallet
     * @param balance the balance of the wallet
     */
    public Wallet(String name, String publicKey, Integer balance) {
        this.name = name;
        this.publicKey = publicKey;
        this.balance = balance;
    }

    /**
     * Returns the name of the wallet.
     *
     * @return the name of the wallet
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the wallet.
     *
     * @param name the name of the wallet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the public key of the wallet.
     *
     * @return the public key of the wallet
     */
    public String getPublicKey() {
        return this.publicKey;
    }

    /**
     * Sets the public key of the wallet.
     *
     * @param hash the public key of the wallet
     */
    public void setPublicKey(String hash) {
        this.publicKey = hash;
    }

    /**
     * Returns the balance of the wallet.
     *
     * @return the balance of the wallet
     */
    public Integer getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance of the wallet.
     *
     * @param balance the balance of the wallet
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * Returns a string representation of the wallet.
     *
     * @return a string representation of the wallet
     */
    @Override
    public String toString() {
        return "u: " + this.name
                + ", PK:" + this.publicKey
                + ", balance: " + this.balance;
    }

}
