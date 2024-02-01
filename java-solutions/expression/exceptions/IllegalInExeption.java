package expression.exceptions;

public class IllegalInExeption extends RuntimeException{
    public IllegalInExeption(String operations){
        super(operations);
    }
}
