package Test;

import model.Player;
import model.PokerGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PokerGameTest {
    private PokerGame testPokerGame;
    private String date;
    private Player player1;
    private Player player2;

    @BeforeEach
    void runBefore() {
        date = "10/09/2023";
        testPokerGame = new PokerGame(date);
        player1 = new Player("Player1");
        player2 = new Player("Player2");
    }

    @Test
    void testConstructor() {
       assertEquals(date, testPokerGame.getDate());
       assertEquals(0, testPokerGame.getPlayers().size());
    }

    @Test
    void testAddPlayer() {
        testPokerGame.addPlayer(player1);
        assertEquals(1, testPokerGame.getPlayers().size());
        testPokerGame.addPlayer(player2);
        assertEquals(2, testPokerGame.getPlayers().size());
        assertEquals(player1, testPokerGame.getPlayers().get(0));
        assertEquals(player2, testPokerGame.getPlayers().get(1));
    }


}
