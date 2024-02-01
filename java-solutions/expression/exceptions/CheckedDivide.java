package expression.exceptions;

import expression.AbstractOperations;
import expression.GlobalInterface;

public class CheckedDivide extends AbstractOperations {
    public CheckedDivide(GlobalInterface exp1, GlobalInterface exp2){
        super(exp1, exp2, "/");
    }

    @Override
    public int Calculate(int n1, int n2) {
        if (((n1 == Integer.MIN_VALUE) && (n2 == -1))) throw new OverflowExeptions("/");
        return n1 / n2;
    }
}