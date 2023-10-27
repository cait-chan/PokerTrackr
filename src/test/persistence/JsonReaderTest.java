package persistence;

import model.Player;
import model.PokerGame;
import model.PokerGameCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PokerGameCollection pokerGameCollection = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            System.out.println("Success, IOException expected and thrown!");
        }
    }

    @Test
    void testReaderEmptyPokerGameCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPokerGameCollection.json");
        try {
            PokerGameCollection pokerGameCollection = reader.read();
            assertEquals(0, pokerGameCollection.getNumPokerGames());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPokerGameCollection.json");
        try {
            PokerGameCollection pokerGameCollection = reader.read();
            List<PokerGame> games = pokerGameCollection.getPokerGames();
            assertEquals(2, games.size());
            List<Player> players1 = pokerGameCollection.getPokerGames().get(0).getPlayers();
            List<Player> players2 = pokerGameCollection.getPokerGames().get(1).getPlayers();
            List<Integer> buyIns1 = pokerGameCollection.getPokerGames().get(0).getBuyIns();
            List<Integer> buyIns2 = pokerGameCollection.getPokerGames().get(1).getBuyIns();
            List<Integer> cashOuts1 = pokerGameCollection.getPokerGames().get(0).getCashOuts();
            List<Integer> cashOuts2 = pokerGameCollection.getPokerGames().get(1).getCashOuts();
            checkPokerGame("10/SEP/2023", players1, buyIns1, cashOuts1, games.get(0));
            checkPokerGame("15/SEP/2023", players2, buyIns2, cashOuts2, games.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
