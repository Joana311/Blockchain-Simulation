package Node;

import Wallet.*;
import Transaction.*;

public class MiningNode extends Node {

    private Integer quantity;

    public MiningNode(Wallet wallet, Integer quantity) {
        super(wallet);
        this.quantity = quantity;
    }

    /*________________________________*/
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /*________________________________*/
    @Override
    public String toString() {
        return this.getWallet().toString()
                + " | @MiningNode#" + String.format("%03d", this.getId());
    }
}
