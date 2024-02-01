package expression.exceptions;

import expression.AbstractParametrs;
import expression.GlobalInterface;

import java.util.Objects;

public class l0 extends AbstractParametrs {
    public GlobalInterface exp;
    public l0(GlobalInterface exp){
        this.exp = exp;
    }
    @Override
    public int evaluate(int n) {
        return Calculate(exp.evaluate(n));
    }
    public int evaluate(int n1, int n2, int n3) {
        return Calculate(exp.evaluate(n1, n2, n3));
    }
    public int Calculate(int n){
        if (n == 0) return 32;
        return 32 - Integer.toBinaryString(n).length();
    }

    @Override
    public String toString() {
        return "l0(" + exp.toString() + ")";
    }
    @Override
    public int hashCode() {
        return Objects.hash(exp);
    }
}