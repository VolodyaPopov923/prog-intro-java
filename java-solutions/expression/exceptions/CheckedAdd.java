package expression.exceptions;
import expression.AbstractOperations;
import expression.GlobalInterface;


public class CheckedAdd extends AbstractOperations {

    public CheckedAdd(GlobalInterface exp1, GlobalInterface exp2){
        super(exp1, exp2, "+");
    }
    @Override
    public int Calculate(int n1, int n2){
        if (n1 > 0 && n2 > Integer.MAX_VALUE - n1 || n1 < 0 && n2 < Integer.MIN_VALUE - n1){
            throw new OverflowExeptions("+");
        }
        return n1 + n2;
    }
}
