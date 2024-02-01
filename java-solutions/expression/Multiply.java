package expression;

public class Multiply extends AbstractOperations {
    public Multiply(GlobalInterface exp1, GlobalInterface exp2){
        super(exp1, exp2, "*");
    }

    @Override
    public int Calculate(int n1, int n2) {
        return n1 * n2;
    }
}
