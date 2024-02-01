package game;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Dear player, I suggest you play tic-tac-toe on a matrix of dimension m * n, to win you need to collect k of your figures in a row.");
        System.out.println("The parameters must be natural numbers, enter them in one line separated by a space.");
        System.out.println("Enter the parameters m, n, k.");
        int m, n, k;
        while (true) {
            m = 0;
            n = 0;
            k = 0;
            Scanner sc = new Scanner(System.in);
            try {
                m = Integer.parseInt(sc.next());
                n = Integer.parseInt(sc.next());
                k = Integer.parseInt(sc.next());
                if (m > 0 && n > 0 && k > 0 && k <= Math.max(m, n)) break;
                System.out.println("Please, enter valid parameters:");
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Unfortunately the parameters turned out to be not parameters.");
                System.out.println("___check that your parameters are natural numbers that fit in the int format___");
                System.out.println("Please, enter valid parameters:");
            } catch (NoSuchElementException e){
                System.out.print("You have completed the program.");
                System.exit(0);
            }
        }
        System.out.println("Enter count of players / bots");
        int countP;
        int count_bot;
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                countP = Integer.parseInt(sc.next());
                count_bot = Integer.parseInt(sc.next());
                if (countP >= 0 && count_bot >= 0 && count_bot + countP > 0) break;
                System.out.println("Please, enter valid parameters:");
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Unfortunately the parameter turned out to be not parameter.");
                System.out.println("___check that your parameter are natural numbers that fit in the int format___");
                System.out.println("Please, enter valid parameter:");
            } catch (NoSuchElementException e){
                System.out.print("You have completed the program.");
                System.exit(0);
            }
        }
        Tournament tournament = new Tournament(countP, count_bot, m, n, k);
        tournament.play();
    }
}