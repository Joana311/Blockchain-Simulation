package BlockchainComponents;

import Notifications.ValidateBlockRq;
import Notifications.ValidateBlockRes;
import ValidateMethods.IValidateMethod;
import MiningMethods.IMiningMethod;
import Wallet.*;
import Block.*;
import java.util.*;
import Transaction.*;

/**
 * This is the MiningNode class that extends the Node class.
 * It represents a mining node in a blockchain.
 *
 * @author Gonzalo Jimenez, Luis Pastor
 */
public class MiningNode extends Node {

    Integer balance;
    private final List<Block> validatedBlock = new ArrayList<>();
    IMiningMethod miningMethod;
    IValidateMethod validationMethod;

/**
 * MiningNode class constructor.
 * Initializes a new MiningNode with the provided wallet and balance.
 *
 * @param wallet The wallet of the MiningNode.
 * @param balance The balance of the MiningNode.
 */
    public MiningNode(Wallet wallet, Integer balance) {
        super(wallet);
        this.balance = balance;
        this.miningMethod = null;
        this.validationMethod = null;
    }

    /**
 * Retrieves the balance of the MiningNode.
 *
 * @return The balance of the MiningNode.
 */
    public Integer getBalance() {
        return this.balance;
    }

/**
 * Sets the balance of the MiningNode.
 *
 * @param balance The balance to set.
 */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

/**
 * Retrieves the list of validated blocks of the MiningNode.
 *
 * @return The list of validated blocks.
 */
    public List<Block> getValidatedBlock() {
        return this.validatedBlock;
    }

/**
 * Sets the mining method of the MiningNode.
 *
 * @param miningMethod The mining method to set.
 */
    public void setMiningMethod(IMiningMethod miningMethod) {
        this.miningMethod = miningMethod;
    }

/**
 * Sets the validation method of the MiningNode.
 *
 * @param validationMethod The validation method to set.
 */
    public void setValidationMethod(IValidateMethod validationMethod) {
        this.validationMethod = validationMethod;
    }

    /**
 * Mines a block with a transaction.
 * If the MiningNode doesn't have a mining method, it stops.
 * Otherwise, it mines a block and broadcasts a ValidateBlockRq to the network.
 *
 * @param transaction The transaction to mine.
 */
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

/**
 * Validates a block.
 * If the MiningNode is the one that created the block, it does nothing.
 * If the MiningNode doesn't have a validation method, it stops.
 * Otherwise, it validates the block and broadcasts a ValidateBlockRes to the network.
 *
 * @param validateBlockRq The ValidateBlockRq to validate.
 */
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

/**
 * Returns a string representation of the MiningNode.
 * The string contains "MiningNode#" and the formatted ID of the MiningNode.
 *
 * @return A string representation of the MiningNode.
 */
    @Override
    public String fullName() {
        return "MiningNode#" + this.formatId();
    }

}
