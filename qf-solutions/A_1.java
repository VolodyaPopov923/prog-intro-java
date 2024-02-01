import java.util.Scanner;

public class AccurateMovement {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)){
            Double a = Double.valueOf(sc.nextInt());
            Double b = Double.valueOf(sc.nextInt());
            Double n = Double.valueOf(sc.nextInt());
            System.out.println((int) Math.ceil((n - b) / (b - a)) * 2 + 1);
        } 
    }
}
