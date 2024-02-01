package expression.parser;
import expression.*;
import java.util.*;

public class ExpressionParser implements TripleParser {
    private final Deque<Character> stackOperations = new LinkedList<>();
    private final Deque<GlobalInterface> stackObj = new LinkedList<>();
    private final HashMap<Character, Integer> map = new HashMap<>(Map.of(
            '@', 100,
            '*', 10, '/', 10,
            '+', 5, '-', 5,
            '&', 4,
            '^', 3,
            '|', 2,
            '(', -1
    ));
    @Override
    public TripleExpression parse(String string) {
        StringBuilder sb = new StringBuilder();
        char lastToken = '@';
        for (int i = 0; i < string.length(); i++){
            Character ch = string.charAt(i);
            if (ch == '-' && Character.isDigit(string.charAt(i + 1)) && (i == 0 || map.containsKey(lastToken))) {
                sb.append('-');
                lastToken = '-';
                continue;
            } else if (ch == '-' && (map.containsKey(lastToken) || i == 0)){
                ch = '@';
                if (stackOperations.isEmpty()){
                    stackOperations.push(ch); continue;}
                while (map.get(ch) < map.get(stackOperations.peek())) {
                    AddObj(Character.toString(stackOperations.pop()));
                    if (stackOperations.isEmpty()) break;
                }
                stackOperations.push(ch);
            }
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else if(!sb.isEmpty()){
                AddObj(sb.toString());
                sb.setLength(0);
            }
            if (map.containsKey(ch) && ch != '@'){
                if (stackOperations.isEmpty() || ch == '(' || map.get(ch) > map.get(stackOperations.peek())){
                    stackOperations.push(ch);
                } else {
                    while (map.get(ch) <= map.get(stackOperations.peek())) {
                        AddObj(Character.toString(stackOperations.pop()));
                        if (stackOperations.isEmpty()) break;
                    }
                    stackOperations.push(ch);
                }
            } else if (ch == ')') {
                while (map.get(stackOperations.peek()) != -1) {
                    AddObj(Character.toString(stackOperations.pop()));
                }
                stackOperations.pop();
            } else if (ch == 'x' || ch == 'y' || ch == 'z'){
                AddObj(Character.toString(ch));
            }
            if (!Character.isWhitespace(ch)) lastToken = ch;
        }
        if (!sb.isEmpty()) AddObj(sb.toString());
        while (!stackOperations.isEmpty()) AddObj(Character.toString(stackOperations.pop()));
        return stackObj.pop();
    }
    private void AddObj(String str){
        switch (str) {
            case "x", "y", "z" -> stackObj.push(new Variable(str));
            case "+", "-", "/", "*", "&", "|", "^", "@" ->{
                GlobalInterface ell = stackObj.pop();
                switch (str){
                    case "+" -> stackObj.push(new Add(stackObj.pop(), ell));
                    case "-" -> stackObj.push(new Subtract(stackObj.pop(), ell));
                    case "/" -> stackObj.push(new Divide(stackObj.pop(), ell));
                    case "*" -> stackObj.push(new Multiply(stackObj.pop(), ell));
                    case "&" -> stackObj.push(new BitwiseAnd(stackObj.pop(), ell));
                    case "|" -> stackObj.push(new BitwiseOr(stackObj.pop(), ell));
                    case "^" -> stackObj.push(new BitwiseXor(stackObj.pop(), ell));
                    case "@" -> stackObj.push(new UnaryMinus(ell));
                }
            }
            default -> stackObj.push(new Const(Integer.parseInt(str)));
        }
    }
}