import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Wspp {
    public static void main(String[] FileInNames){
        try{
            Scanner sc = new Scanner(new FileInputStream(FileInNames[0]));
            LinkedHashMap<String, ArrayList<String>> LinkedMap = new LinkedHashMap<>();
            int cnt = 0;
            while (sc.hasNextWord()) {
                String String_value = sc.nextWord().toLowerCase();
                if (!LinkedMap.containsKey(String_value)) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("1"); 
                    strings.add(Integer.toString(cnt + 1));
                    LinkedMap.put(String_value, strings);
                } else {
                    ArrayList<String> strings = LinkedMap.get(String_value);
                    strings.add(Integer.toString(cnt + 1));
                    strings.set(0, Integer.toString(Integer.parseInt(strings.get(0)) + 1));
                    LinkedMap.put(String_value, strings);
                }
            cnt++;
            }
            sc.close();
            BufferedWriter wr = new BufferedWriter(new FileWriter(FileInNames[1], StandardCharsets.UTF_8, false));
            for (String i : LinkedMap.keySet()){
                wr.write(i);
                for (String j : LinkedMap.get(i)){
                    wr.write(" ");
                    wr.write(j);    
                }
                wr.write(System.lineSeparator());
            }
            wr.close();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}