/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Node;

import Wallet.*;
import Transaction.*;

/**
 *
 * @author Luizz^^
 */
public class MiningNode extends Node{
    public MiningNode (Wallet wallet) {
        super(wallet);
    }
    
    @Override
    public String toString() {
        return this.getWallet().toString()
                + " | @MiningNode#" + String.format("%03d", this.getId());
    }
}
