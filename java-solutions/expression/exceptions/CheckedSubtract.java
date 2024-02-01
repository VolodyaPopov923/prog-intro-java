package expression.exceptions;

import expression.AbstractOperations;
import expression.GlobalInterface;

public class CheckedSubtract extends AbstractOperations {
    public CheckedSubtract(GlobalInterface exp1, GlobalInterface exp2){
        super(exp1, exp2, "-");
    }

    @Override
    public int Calculate(int n1, int n2) {
        if ((n2 < 0 && n1 > Integer.MAX_VALUE + n2) || (n2 > 0 && n1 < Integer.MIN_VALUE + n2)){
            throw new OverflowExeptions("-");
        }
        return n1 - n2;
    }
}