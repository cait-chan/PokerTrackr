package Test;

import model.PokerGame;
import model.PokerGameCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerGameCollectionTest {
    private PokerGameCollection testPokerGameCollection;
    private PokerGame pokerGame1;
    private PokerGame pokerGame2;

    @BeforeEach
    void runBefore() {
        testPokerGameCollection = new PokerGameCollection();
        pokerGame1 = new PokerGame("10/SEP/2023");
        pokerGame2 = new PokerGame("15/SEP/2023");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testPokerGameCollection.getNumPokerGames());
    }

    @Test
    void testAddPokerGame() {
        testPokerGameCollection.addPokerGame(pokerGame1);
        assertEquals(1, testPokerGameCollection.getNumPokerGames());
        assertEquals(pokerGame1, testPokerGameCollection.getPokerGames().get(0));
    }

    @Test
    void testRemovePokerGame() {
        testPokerGameCollection.addPokerGame(pokerGame1);
        testPokerGameCollection.addPokerGame(pokerGame2);
        assertEquals(2, testPokerGameCollection.getNumPokerGames());
        testPokerGameCollection.removePokerGame(pokerGame1);
        assertEquals(1, testPokerGameCollection.getNumPokerGames());
        assertEquals(pokerGame2, testPokerGameCollection.getPokerGames().get(0));
    }

}
