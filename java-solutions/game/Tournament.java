package game;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private static int countP, count_bot, m, n, k;


    public Tournament(int countP, int count_bot, int m, int n, int k){
        Tournament.countP = countP;
        Tournament.count_bot = count_bot;
        Tournament.m = m;
        Tournament.n = n;
        Tournament.k = k;
    }
    public void play() {
        List<Player> listPlayers = new ArrayList<>();
        List<Integer> listCountPlayers = new ArrayList<>();
        for (int i = 1; i <= countP; i++){
            listCountPlayers.add(i);
            listPlayers.add(new HumanPlayer());
        }
        int cnt = listCountPlayers.size();
        for (int i = listCountPlayers.size() + 1; i <= cnt + count_bot; i++){
            listCountPlayers.add(i);
            listPlayers.add(new RandomPlayer(m, n));
        }
        for (int i = 1; i <= degree2(countP + count_bot); i++){
            cnt = listCountPlayers.size() % 2;
            for (int j = 0; j < listCountPlayers.size() - cnt; j++){
                final Game game = new Game(false, listPlayers.get(j), listPlayers.get(j + 1), listCountPlayers.get(j), listCountPlayers.get(j + 1));
                int result;
                do {
                    result = game.playRandomTour(new MNKBoard(m, n, k));
                } while (result != listCountPlayers.get(j) && result != listCountPlayers.get(j + 1));
                System.out.println("Player lose with number " + (result == listCountPlayers.get(j) ? listCountPlayers.get(j + 1) : listCountPlayers.get(j))
                + " and took a place by number " + (degree2(countP + count_bot) - i + 2));
                listCountPlayers.remove(result == listCountPlayers.get(j) ? j + 1 : j);
                listPlayers.remove(result == listCountPlayers.get(j) ? j + 1 : j);
            }
        }
        System.out.println("Player with number " + listCountPlayers.get(0) + " win");
    }
    public static int degree2(int n){
        int num = 1;
        int cnt = 0;
        while (num < n){
            num *= 2;
            cnt++;
        }
        return cnt;
    }
}
