package Test;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player testPlayer;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player("Player");
    }

    @Test
    void testConstructor() {
        assertEquals("Player", testPlayer.getName());
        assertEquals(0, testPlayer.getTotalBuyIns());
        assertEquals(0, testPlayer.getTotalCashOuts());
        assertEquals(0, testPlayer.getGamesWon());
        assertEquals(0, testPlayer.getGamesLost());
    }

    @Test
    void testUpdateTotalBuyInsOnce() {
        testPlayer.increaseTotalBuyIns(20);
        assertEquals(20, testPlayer.getTotalBuyIns());
    }

    @Test
    void testUpdateTotalBuyInsMultipleTimes() {
        testPlayer.increaseTotalBuyIns(20);
        testPlayer.increaseTotalBuyIns(50);
        testPlayer.increaseTotalBuyIns(100);
        testPlayer.decreaseTotalBuyIns(30);
        assertEquals(140, testPlayer.getTotalBuyIns());
    }

    @Test
    void testUpdateTotalCashOutsOnce() {
        testPlayer.increaseTotalCashOuts(30);
        assertEquals(30, testPlayer.getTotalCashOuts());
    }

    @Test
    void testUpdateTotalCashOutsMultipleTimes() {
        testPlayer.increaseTotalCashOuts(30);
        testPlayer.increaseTotalCashOuts(60);
        testPlayer.increaseTotalCashOuts(90);
        testPlayer.decreaseTotalCashOuts(20);
        assertEquals(160, testPlayer.getTotalCashOuts());
    }
}