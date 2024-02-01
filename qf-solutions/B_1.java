import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = -710 * 25000; i < -710 * 25000 + 710 * n; i += 710){
            System.out.println(i);
        }
    }
}
