package persistence;

import model.Player;
import model.PokerGame;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPokerGame(String date, List<Player> players, List<Integer> buyIns,
                                  List<Integer> cashOuts, PokerGame pokerGame) {
        assertEquals(date, pokerGame.getDate());
        for (int i = 0; i < players.size(); i++) {
            assertEquals(players.get(i), pokerGame.getPlayers().get(i));
        }
        for (int i = 0; i < buyIns.size(); i++) {
            assertEquals(buyIns.get(i), pokerGame.getBuyIns().get(i));
        }
        for (int i = 0; i < cashOuts.size(); i++) {
            assertEquals(cashOuts.get(i), pokerGame.getCashOuts().get(i));
        }
    }
}
