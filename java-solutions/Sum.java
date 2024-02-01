public class Sum {
    public static void main (String[] args) {
        int summ = 0;
        for(String arg : args) {
            StringBuilder string = new StringBuilder();
            for(int i = 0; i < arg.length(); i++){
                if (Character.isWhitespace(arg.charAt(i))) {
                    summ += (string.length() != 0 ? Integer.parseInt(string.toString()) : 0);
                    string.setLength(0);
                    continue;
                }
                string.append(arg.charAt(i));
                }
            summ += (string.length() != 0 ? Integer.parseInt(string.toString()) : 0);
        }
        System.out.println(summ);
    }
}