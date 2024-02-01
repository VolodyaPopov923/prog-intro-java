import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WsppPosition {
    public static void main(String[] FileInNames){
        try{
            Scanner sc = new Scanner(new FileInputStream(FileInNames[0]));
            LinkedHashMap<String, ArrayList<String>> LinkedMap = new LinkedHashMap<>();
            int cntline = 0;
            while (sc.hasNextLine()){
                int cnt = 0;
                ArrayList<String> stringsAll = new ArrayList<>();
                Scanner s2 = new Scanner(sc.nextLine());
                while (s2.hasNextWord()){
                    stringsAll.add(cnt, s2.nextWord());
                    cnt++;
                }
                cntline++;
                s2.close();
                int cnt2 = 0;
                for (int i = 0; i < stringsAll.size(); i++){
                    String String_value = stringsAll.get(i).toLowerCase();
                    if (!LinkedMap.containsKey(String_value)){
                        ArrayList<String> strings = new ArrayList<>();
                        strings.add("1");
                        strings.add(Integer.toString(cntline) + ":" + Integer.toString(cnt - cnt2));
                        LinkedMap.put(String_value, strings);
                    } else{
                        ArrayList<String> strings = LinkedMap.get(String_value);
                        strings.set(0, Integer.toString(Integer.parseInt(strings.get(0)) + 1));
                        strings.add(Integer.toString(cntline) + ":" + Integer.toString(cnt - cnt2));
                        LinkedMap.put(String_value, strings);
                    }
                    cnt2++;
            }
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