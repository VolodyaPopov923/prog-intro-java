import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class I {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            int n = Integer.parseInt(br.readLine());
            ArrayList<Integer> xlm = new ArrayList<>(n);
            ArrayList<Integer> xrm = new ArrayList<>(n);
            ArrayList<Integer> ylm = new ArrayList<>(n);
            ArrayList<Integer> yrm = new ArrayList<>(n);
            while (n > 0){
                n--;
                //
                String line = br.readLine();
                StringBuilder sb = new StringBuilder();
                int cnti = 0;
                int[] ints = new int[3];
                for (char i : line.toCharArray()){
                    if (!Character.isWhitespace(i)) {sb.append(i); continue;}
                    if (sb.length() != 0){
                        ints[cnti] = Integer.parseInt(sb.toString());
                        cnti++;
                        sb.setLength(0);
                    }
                }
                if (sb.length() != 0) ints[cnti] = Integer.parseInt(sb.toString());
                //
                int xi = ints[0];
                int yi = ints[1];
                int hi = ints[2];
                xlm.add(xi - hi);
                xrm.add(xi + hi);
                ylm.add(yi - hi);
                yrm.add(xi + hi);
            }
            double xl = Collections.min(xlm);
            double xr = Collections.max(xrm);
            double yl = Collections.min(ylm);
            double yr = Collections.max(yrm);
            int x = (int) (xl + xr) / 2;
            int y = (int) (yl + yr) / 2;
            int h = (int) Math.ceil(Math.max(xr - xl, yr - yl) / 2);
            System.out.print(x + " " + y + " " + h);
        } catch(IOException e){
            System.err.println("Io" + e.getMessage());
        }
    }
}
