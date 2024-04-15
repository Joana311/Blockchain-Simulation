package MiningMethods;

import Block.Block;
import CommonUtils.BlockConfig;
import CommonUtils.CommonUtils;
import Transaction.Transaction;

public class SimpleMining implements IMiningMethod {

    public SimpleMining() {
    }

    /**
     * Function to create a block hash
     *
     * @param block Block te create the hash
     * @return The hash
     */
    @Override
    public String createHash(Block block) {
        String buffer;

        buffer = block.getVersion().toString()
                + (block.getPrevious() != null ? block.getPrevious().getHash() : BlockConfig.GENESIS_BLOCK)
                + block.getTimestamp()
                + block.getDifficulty()
                + block.getNonce();

        return CommonUtils.sha256(buffer);
    }

    /**
     * Function to create a block to a transaction
     *
     * @param transaction Transaction to do
     * @param previousConfirmedBlock Previous block confirmed
     * @param minerKey Key of the miner node
     * @return Block created
     */
    @Override
    public Block mineBlock(Transaction transaction, Block previousConfirmedBlock, String minerKey) {
        Block block = new Block(transaction);

        block.setPrevious(previousConfirmedBlock);
        block.setMinerKey(minerKey);
        block.setHash(this.createHash(block));

        return block;
    }

    /*____________________________________________________________________*/
}
