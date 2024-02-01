package expression.exceptions;
import expression.*;
import java.util.*;

public class ExpressionParser implements TripleParser {
    private Deque<GlobalInterface> stackObj;
    private Deque<String> stackOperations;
    private final HashMap<String, Integer> map = new HashMap<>(Map.of(
            "@", 100, "l0", 100, "t0", 100,
            "*", 10, "/", 10,
            "+", 5, "-", 5,
            "(", -1
    ));
    @Override
    public TripleExpression parse(String string) {
        stackObj = new LinkedList<>();
        stackOperations = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        String lastToken = "@";
        int cntBracket = 0;
        boolean flag = false;
        for (int i = 0; i < string.length(); i++){
            if (flag){
                flag = false;
                continue;
            }
            Character ch = string.charAt(i);
            if (ch == 'l' || ch == 't'){
                if (string.charAt(i + 1) == '0'
                && (Character.isWhitespace(string.charAt(i + 2)) || string.charAt(i + 2) == '(')){
                    flag = true;
                    if (stackOperations.isEmpty()){
                        stackOperations.push(ch + "0");
                        continue;
                    }
                    SiftStack(ch + "0");
                    continue;
                } else{
                    throw new IllegalInExeption("IllegalIn");
                }
            }
            if (!Character.isDigit(ch) && (!map.containsKey(ch.toString()) || ch == '@') && ch != ')' && ch != 'x' && ch != 'y' && ch != 'z' && !Character.isWhitespace(ch)){
                throw new IllegalInExeption("IllegalIn");
            }
            if (cntBracket < 0){
                throw new IllegalInExeption("IllegalIn");
            }
            if (ch == '(') {
                cntBracket++;
            }else if (ch == ')'){
                cntBracket--;
            }
            if (ch == '-' && Character.isDigit(string.charAt(i + 1)) && (i == 0 || map.containsKey(lastToken))) {
                sb.append('-');
                lastToken = "-";
                continue;
            } else if (ch == '-' && (map.containsKey(lastToken) || i == 0)){
                ch = '@';
                if (stackOperations.isEmpty()){
                    stackOperations.push(ch.toString()); continue;}
                SiftStack(ch.toString());
            }
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else if(!sb.isEmpty()){
                AddObj(sb.toString());
                sb.setLength(0);
            }
            if (map.containsKey(ch.toString()) && ch != '@'){
                if (stackOperations.isEmpty() || ch == '(' || map.get(ch.toString()) > map.get(stackOperations.peek())){
                    stackOperations.push(ch.toString());
                } else {
                    while (map.get(ch.toString()) <= map.get(stackOperations.peek())) {
                        AddObj(stackOperations.pop());
                        if (stackOperations.isEmpty()) break;
                    }
                    stackOperations.push(ch.toString());
                }
            } else if (ch == ')') {
                while (map.get(stackOperations.peek()) != -1) {
                    AddObj(stackOperations.pop());
                }
                stackOperations.pop();
            } else if (ch == 'x' || ch == 'y' || ch == 'z'){
                AddObj(Character.toString(ch));
            }
            if (!Character.isWhitespace(ch)) lastToken = ch.toString();
        }
        if (cntBracket != 0){
            throw new IllegalInExeption("IllegalIn");
        }
        if (!sb.isEmpty()) AddObj(sb.toString());
        while (!stackOperations.isEmpty()) AddObj(stackOperations.pop());
        if (stackObj.size() != 1) throw new IllegalInExeption("IllegalIn");
        return stackObj.pop();
    }
    private void AddObj(String str){
        switch (str) {
            case "x", "y", "z" -> stackObj.push(new Variable(str));
            case "+", "-", "/", "*", "@", "l0", "t0" ->{
                GlobalInterface ell = stackObj.pop();
                switch (str){
                    case "+" -> stackObj.push(new CheckedAdd(stackObj.pop(), ell));
                    case "-" -> stackObj.push(new CheckedSubtract(stackObj.pop(), ell));
                    case "/" -> stackObj.push(new CheckedDivide(stackObj.pop(), ell));
                    case "*" -> stackObj.push(new CheckedMultiply(stackObj.pop(), ell));
                    case "@" -> stackObj.push(new CheckedNegate(ell));
                    case "l0" -> stackObj.push(new l0(ell));
                    case "t0" -> stackObj.push(new t0(ell));
                }
            }
            default -> stackObj.push(new Const(Integer.parseInt(str)));
        }
    }
    private void SiftStack(String str){
        while (map.get(str) < map.get(stackOperations.peek())) {
            AddObj(stackOperations.pop());
            if (stackOperations.isEmpty()) break;
        }
        stackOperations.push(str);
    }
}