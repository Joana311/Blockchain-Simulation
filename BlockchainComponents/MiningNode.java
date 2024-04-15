package BlockchainComponents;

import Notifications.ValidateBlockRq;
import Notifications.ValidateBlockRes;
import ValidateMethods.IValidateMethod;
import MiningMethods.IMiningMethod;
import Wallet.*;
import Block.*;
import java.util.*;
import Transaction.*;

public class MiningNode extends Node {

    Integer balance;
    /*private List<Transaction> validatedTransactions = new ArrayList<>();*/
    private final List<Block> validatedBlock = new ArrayList<>();
    IMiningMethod miningMethod;
    IValidateMethod validationMethod;

    public MiningNode(Wallet wallet, Integer balance) {
        super(wallet);
        this.balance = balance;
        this.miningMethod = null;
        this.validationMethod = null;
    }

    /*____________________________________________________________________*/
    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<Block> getValidatedBlock() {
        return this.validatedBlock;
    }

    public void setMiningMethod(IMiningMethod miningMethod) {
        this.miningMethod = miningMethod;
    }

    public void setValidationMethod(IValidateMethod validationMethod) {
        this.validationMethod = validationMethod;
    }

    /*____________________________________________________________________*/
    @Override
    protected void nodeMine(Transaction transaction) {

        /* If the node dont have methods, stop */
        if (this.miningMethod == null) {
            return;
        }

        /* Get the info to mine and mine */
        Block lastValidatedBlock = (this.validatedBlock.isEmpty() ? null : this.validatedBlock.getLast());
        this.validatedTransactions.add(transaction);
        Block minedBlock = this.miningMethod.mineBlock(transaction, lastValidatedBlock,
                this.getWallet().getPublicKey());

        /* Print the state and comunicate it to all the network */
        System.out.println("[" + this.fullName() + "] Mined block: " + minedBlock);
        this.getTopParent().broadcast(new ValidateBlockRq(minedBlock, this));
    }

    @Override
    protected void nodeValidateBlock(ValidateBlockRq validateBlockRq) {
        /* If the node is the one that created the block, dont do nothing */
        if (validateBlockRq.getNode() == this) {
            System.out.println("[" + this.fullName() + "] You cannot validate your own block");
            return;
        }

        /* If it is another node, it will validate it (if it has a validate method) */
        if (this.validationMethod == null) {
            return;
        }

        /* Get the validate status */
        Boolean state = this.validationMethod.validate(this.miningMethod,
                validateBlockRq.getBlock());

        /* Comunicate it to the rest of the network */
        ValidateBlockRes response = new ValidateBlockRes(validateBlockRq.getBlock(), state, this);
        System.out.println("[" + this.fullName() + "] Emitted Task: " + response.getMessage());
        this.getTopParent().broadcast(response);
    }

    @Override
    public String fullName() {
        return "MiningNode#" + this.formatId();
    }

}
