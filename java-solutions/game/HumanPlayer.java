package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int firstP, secondP;
            Scanner sc = new Scanner(System.in);
            while (true) {
                firstP = 0;
                secondP = 0;
                try {
                    firstP = Integer.parseInt(sc.next());
                    secondP = Integer.parseInt(sc.next());
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("enter valid params");
                    sc.nextLine();
                } 
            }
            sc.close();
            final Move move = new Move(firstP, secondP, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
