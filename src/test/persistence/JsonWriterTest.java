package persistence;

import model.Player;
import model.PokerGame;
import model.PokerGameCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PokerGameCollection pokerGameCollection = new PokerGameCollection();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            System.out.println("Success! IOException expected and thrown");
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PokerGameCollection pokerGameCollection = new PokerGameCollection();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(pokerGameCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            pokerGameCollection = reader.read();
            assertEquals(0, pokerGameCollection.getNumPokerGames());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PokerGameCollection pokerGameCollection = new PokerGameCollection();
            PokerGame pokerGame1 = new PokerGame("10/SEP/2023");
            PokerGame pokerGame2 = new PokerGame("15/SEP/2023");
            pokerGame1.addPlayer(new Player("Johnny"));
            pokerGame2.addPlayer(new Player("Caitlyn"));
            pokerGame1.addBuyIn(25);
            pokerGame2.addBuyIn(100);
            pokerGame1.addCashOut(50);
            pokerGame2.addCashOut(1000);
            pokerGameCollection.addPokerGame(pokerGame1);
            pokerGameCollection.addPokerGame(pokerGame2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(pokerGameCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            pokerGameCollection = reader.read();
            List<PokerGame> pokerGames = pokerGameCollection.getPokerGames();
            assertEquals(2, pokerGames.size());
            checkPokerGame("10/SEP/2023", pokerGames.get(0).getPlayers(), pokerGames.get(0).getBuyIns(),
                    pokerGames.get(0).getCashOuts(), pokerGames.get(0));
            checkPokerGame("15/SEP/2023", pokerGames.get(1).getPlayers(), pokerGames.get(1).getBuyIns(),
                    pokerGames.get(1).getCashOuts(), pokerGames.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
