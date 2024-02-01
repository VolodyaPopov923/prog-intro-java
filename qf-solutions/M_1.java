import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;


public class M {
    public static void main(String[] args) {
        try (BufferedReader sc = new BufferedReader(new InputStreamReader(System.in))){
            int t = Integer.parseInt(sc.readLine());
            while (t > 0) {
                t--;
                sc.readLine();
                ArrayList<Integer> lst = new ArrayList<>();
                Scanner sc2 = new Scanner(sc.readLine());
                while (sc2.hasNextInt()) {
                    lst.add(sc2.nextInt());
                }
                int cnt = 0;
                int size = lst.size();
                for (int i = 0; i < size - 2; i++){
                    for (int j = i + 1; j < size - 1; j++){
                        for (int k = j + 1; k < size; k++){
                            if (lst.get(j) - lst.get(i) == lst.get(k) - lst.get(j)) cnt++;
                        } 
                    } 
                }
                System.out.println(cnt);
            }
        
        } catch(IOException e){
            System.err.println("Io" + e.getMessage());
        }
    }
        

}  


