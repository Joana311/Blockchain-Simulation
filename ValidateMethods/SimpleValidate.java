package ValidateMethods;

import MiningMethods.IMiningMethod;
import Block.*;

/**
 * This class represents a simple validation method for blocks.
 * It implements the IValidateMethod interface.
 * 
 * @author Gonzalo Jiménez, Luis Pastor
 */
public class SimpleValidate implements IValidateMethod {

    /**
     * Constructs a new instance of the SimpleValidate class.
     */
    public SimpleValidate() {
    }

    /**
     * Validates a block using a mining method.
     * 
     * @param miningMethod the mining method used to create the hash
     * @param block the block to be validated
     * @return true if the block is valid, false otherwise
     */
    @Override
    public boolean validate(IMiningMethod miningMethod, Block block) {
        String newHash = miningMethod.createHash(block);
        return newHash.equals(block.getHash());
    }
}
