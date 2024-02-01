import java.util.Arrays;

public class ReverseMinRAbc {
    private static String[] append(String[] arg, String element) {
        String[] array = Arrays.copyOf(arg, arg.length * 2);
        array[arg.length] = element;
        return array;
    }
    private static String[][] add(String[][] arg, String[] element) {
        String[][] array = Arrays.copyOf(arg, arg.length * 2);
        array[arg.length] = element;
        return array;
    }
    private static int ConvertToInt(String str){
        String alphabet = "abcdefghij";
        String minus = "";
        if (str.charAt(0) == '-'){
            minus = "-";
            str = str.substring(1);
        }
        StringBuilder n = new StringBuilder();
        for (char i : str.toCharArray()){
            n.append(alphabet.indexOf(i));
        }
        return Integer.parseInt(minus + (n.length() != 0 ? n.toString() : "0"));
    }
    private static String ConvertToString(int n){
        String alphabet = "abcdefghij";
        String minus = "";
        StringBuilder n2 = new StringBuilder();
        if (n < 0){
            minus = "-";
        }
        String str2 =  String. valueOf(Math.abs(n));
        for (char i : str2.toCharArray()){
            n2.append(alphabet.charAt(Integer.parseInt(String.valueOf(i))));
        }
        return minus + (n2.length() != 0 ? n2.toString() : "a");
    }
    public static void main (String[] args) {
        String[][] data = new String[1][];
        String[] data2 = new String[1];
        Scanner sc = new Scanner(System.in);
        int cnt2 = 0;
        while(sc.hasNextLine()) {
            int cnt = 0;
            Scanner s2 = new Scanner(sc.nextLine());
            while(s2.hasNext()){
                if (data2.length == cnt){
                    data2 = append(data2, s2.next());
                }else{
                    data2[cnt] = s2.next();
                }
                cnt++;
            }
            s2.close();
            if(data2.length == 0){
                if (data.length == cnt2){
                    data = add(data, null);
                }else{
                    data[cnt2] = null;
                }
            }else{
                String[] data3 = Arrays.copyOf(data2, cnt);
                if (data.length == cnt2){
                    data = add(data, data3);
                }else{
                    data[cnt2] = data3;
                }
            }
            cnt2++;
            data2 = new String[1];
        }
        sc.close();
        String[][] new_data = Arrays.copyOf(data, cnt2);
        for (int i = 0;  i < new_data.length; i++){
            int min_c = Integer.MAX_VALUE;
            if (new_data[i] != null){
                for (int j = 0;  j < new_data[i].length; j++){
                    min_c = Math.min(ConvertToInt(new_data[i][j]), min_c);
                    System.out.print(ConvertToString(min_c));
                    if (j != new_data[i].length){
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
}