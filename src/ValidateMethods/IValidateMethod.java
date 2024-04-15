package ValidateMethods;

import MiningMethods.IMiningMethod;
import Block.*;

public interface IValidateMethod {

    public boolean validate(IMiningMethod miningMethod, Block block);
}
