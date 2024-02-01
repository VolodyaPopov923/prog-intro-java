package expression.exceptions;

import expression.AbstractOperations;
import expression.GlobalInterface;

public class CheckedMultiply extends AbstractOperations {
    public CheckedMultiply(GlobalInterface exp1, GlobalInterface exp2){
        super(exp1, exp2, "*");
    }

    @Override
    public int Calculate(int n1, int n2) {
        long c = (long) n1 * n2;
        if ((c > Integer.MAX_VALUE) || (c < Integer.MIN_VALUE)){
            throw new OverflowExeptions("*");
        }

        return n1 * n2;
    }
}