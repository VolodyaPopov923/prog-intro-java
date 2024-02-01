package expression;

import java.util.Objects;

public class UnaryMinus extends AbstractParametrs {
    public GlobalInterface exp;
    public UnaryMinus(GlobalInterface exp){
        this.exp = exp;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return -1 * exp.evaluate(x, y, z);
    }
    @Override
    public int evaluate(int x) {
        return -1 * exp.evaluate(x);
    }
    @Override
    public String toString() {
        return "-(" + exp.toString() + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryMinus that = (UnaryMinus) o;
        return Objects.equals(exp, that.exp);
    }
    @Override
    public int hashCode() {
        return Objects.hash(exp);
    }
}
