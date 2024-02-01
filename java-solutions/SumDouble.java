public class SumDouble {
    public static void main (String[] args) {
        double summ = 0.0;
        for(String arg : args) {
            StringBuilder string = new StringBuilder();
            for(int i = 0; i < arg.length(); i++){
                if (Character.isWhitespace(arg.charAt(i))) {
                    summ += (string.length() != 0 ? Double.parseDouble(string.toString()) : 0);
                    string.setLength(0);
                    continue;
                }
                string.append(arg.charAt(i));
                }
            summ += (string.length() != 0 ? Double.parseDouble(string.toString()) : 0);
        }
        System.out.println(summ);
    }
}