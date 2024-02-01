package game;

import java.util.Random;

public class Game {
    private final boolean log;
    private final Player player1, player2;
    private int pl1;
    private int pl2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        if (player1 == null || player2 == null) throw new IllegalArgumentException();
    }
    public Game(final boolean log, final Player player1, final Player player2, int pl1, int pl2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        this.pl1 = pl1;
        this.pl2 = pl2;
        if (player1 == null || player2 == null) throw new IllegalArgumentException();
    }

     public int play(Board board) {
         while (true) {
             final int result1 = move(board, player1, 1);
             if (result1 != -1) {
                 return result1;
             }
             final int result2 = move(board, player2, 2);
             if (result2 != -1) {
                 return result2;
             }
         }
     }
    
    public int playRandomTour(Board board) {
        Random random = new Random();
        boolean r = random.nextBoolean();
        while (true) {
            if (r){
                final int result1 = move(board, player1, pl1);
                if (result1 != -1) {
                    return result1;
                }
                final int result2 = move(board, player2, pl2);
                if (result2 != -1) {
                    return result2;
                }
            } else{
                final int result2 = move(board, player2, pl2);
                if (result2 != -1) {
                    return result2;
                }
                final int result1 = move(board, player1, pl1);
                if (result1 != -1) {      
                    return result1;
                }
            }
        }
    }
    

    private int move(final Board board, final Player player, int no) {
        final Move move;
        if (player == player1){
            no = pl1;
        } else{
            no = pl2;
        }
        try{
            move = player.move(board.getPosition(), board.getCell());
        } catch(Exception e){
            return no;
        }
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    
        
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}