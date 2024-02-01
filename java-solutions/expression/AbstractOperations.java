package expression;

import java.util.Objects;

public abstract class AbstractOperations extends AbstractParametrs {
    protected GlobalInterface exp1;
    protected GlobalInterface exp2;
    protected String str;


    public AbstractOperations(GlobalInterface exp1, GlobalInterface exp2, String str) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.str = str;
    }

    public int evaluate(int n) {
        return Calculate(exp1.evaluate(n), exp2.evaluate(n));
    }

    public int evaluate(int x, int y, int z) {
        return Calculate(exp1.evaluate(x, y, z), exp2.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + exp1.toString() + " " + str + " " + exp2.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractOperations abstract1 = (AbstractOperations) o;
        return Objects.equals(exp1, abstract1.exp1) && Objects.equals(exp2, abstract1.exp2) && Objects.equals(str, abstract1.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp1, exp2, str);
    }

    public abstract int Calculate(int n1, int n2);
}