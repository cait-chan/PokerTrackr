//Citation: from workroom example application

package persistence;

import model.Player;
import model.PokerGame;
import model.PokerGameCollection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads poker game collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PokerGameCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePokerGameCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses poker game collection from JSON object and returns it
    private PokerGameCollection parsePokerGameCollection(JSONObject jsonObject) {
        PokerGameCollection pokerGameCollection = new PokerGameCollection();
        addPokerGames(pokerGameCollection, jsonObject);
        return pokerGameCollection;
    }

    // MODIFIES: poker game collection
    // EFFECTS: parses poker games from JSON object and adds them to poker game collection
    private void addPokerGames(PokerGameCollection pokerGameCollection, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pokerGames");
        for (Object json : jsonArray) {
            JSONObject nextPokerGame = (JSONObject) json;
            addPokerGame(pokerGameCollection, nextPokerGame);
        }
    }

    // MODIFIES: poker game collection
    // EFFECTS: parses poker game from JSON object and adds it to poker game collection
    private void addPokerGame(PokerGameCollection pokerGameCollection, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        PokerGame pokerGame = new PokerGame(date);
        pokerGameCollection.addPokerGame(pokerGame);
        JSONArray playersJsonArray = jsonObject.getJSONArray("players");
        for (Object json : playersJsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(pokerGame, nextPlayer);
        }
        List<Object> buyIns = jsonObject.getJSONArray("buy-ins").toList();
        addBuyIn(pokerGame, buyIns);

        List<Object> cashOuts = jsonObject.getJSONArray("cash-outs").toList();
        addCashOut(pokerGame, cashOuts);
    }

    //MODIFIES: poker game
    //EFFECTS: parses players from JSON object and adds them to your poker game
    private void addPlayer(PokerGame pokerGame, JSONObject nextPlayer) {
        String name = nextPlayer.getString("name");
        Player player = new Player(name);
        pokerGame.addPlayer(player);
    }

    //MODIFIES: poker game
    //EFFECTS: parses buy ins from JSON object and adds them to your poker game
    private void addBuyIn(PokerGame pokerGame, List<Object> buyIns) {
        for (int i = 0; i < buyIns.size(); i++) {
            pokerGame.addBuyIn((Integer) buyIns.get(i));
        }
    }

    //MODIFIES: poker game
    //EFFECTS: parses cash outs from JSON object and adds them to your poker game
    private void addCashOut(PokerGame pokerGame, List<Object> cashOuts) {
        for (int i = 0; i < cashOuts.size(); i++) {
            pokerGame.addCashOut((Integer) cashOuts.get(i));
        }
    }
}
