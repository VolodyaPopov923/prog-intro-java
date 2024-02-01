import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

public class  WordStatInput {
    private static String[] append_String(String[] arg, String element) {
            String[] array = Arrays.copyOf(arg, arg.length * 2);
            array[arg.length] = element;
            return array;
        }
    private static int[] append_int(int[] arg) {
            int[] array = Arrays.copyOf(arg, arg.length * 2);
            array[arg.length] = 1;
            return array;
        }
    public static void main (String[] args) {
        try (Scanner sc = new Scanner(new FileInputStream(args[0]))){
            StringBuilder string = new StringBuilder();
            String[] data_string = new String[1];
            int[] data_int = new int[1];
            int cnt = 0;
            while (sc.hasNextLine()){
                for (char s : sc.nextLine().toCharArray()) {
                    if((Character.getType(s) == Character.DASH_PUNCTUATION) || (s == '\'') | Character.isLetter(s)) {
                        string.append((char) s);
                    }else {
                        if (!string.isEmpty()){
                            String token2 = string.toString().toLowerCase();
                            if (!Arrays.asList(data_string).contains(token2)){
                                if (cnt == data_string.length){
                                    data_string = append_String(data_string, token2);
                                    data_int = append_int(data_int);
                                }else{
                                    data_string[cnt] = token2;
                                    data_int[cnt] = 1;
                                }
                                cnt++;
                            } else{
                                data_int[Arrays.asList(data_string).indexOf(token2)]++;
                            }
                        }
                        string.setLength(0);
                    }
                }
                if (!string.isEmpty()){
                    String token2 = string.toString().toLowerCase();
                    if (!Arrays.asList(data_string).contains(token2)){
                        if (cnt == data_string.length){
                            data_string = append_String(data_string, token2);
                            data_int = append_int(data_int);
                        }else{
                            data_string[cnt] = token2;
                            data_int[cnt] = 1;
                        }
                        cnt++;
                    } else{
                        data_int[Arrays.asList(data_string).indexOf(token2)]++;
                    }
                }
                string.setLength(0);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8, false))) {
                String[] new_data_string = Arrays.copyOf(data_string, cnt);
                int[] new_data_int = Arrays.copyOf(data_int, cnt);
                for (int i = 0; i < new_data_int.length; i++){
                    String str = new_data_string[i] + " " + new_data_int[i];
                    writer.write(str + System.lineSeparator());
                }
            } catch (IOException e) {
                System.err.println("write IO error " + e.getMessage());
            }
    } catch(IOException e){
        System.err.println("write IO error " + e.getMessage());
    }
    
    }
}