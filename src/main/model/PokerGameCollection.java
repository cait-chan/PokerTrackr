package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerGameCollection implements Writable {
    private List<PokerGame> pokerGameCollection;

    // EFFECTS: creates a new poker game collection with an empty list of poker games
    public PokerGameCollection() {
        pokerGameCollection = new ArrayList<>();
    }

    //REQUIRES: poker game is not currently in collection
    //MODIFIES: this
    //EFFECTS: adds the poker game to the collection
    public void addPokerGame(PokerGame pokerGame) {
        pokerGameCollection.add(pokerGame);
        EventLog.getInstance().logEvent(new Event(pokerGame.getDate()
                + " poker game added to poker game collection"));
    }

    //REQUIRES: poker game must already be in collection
    //MODIFIES: this
    //EFFECTS: removes poker game from the collection
    public void removePokerGame(PokerGame pokerGame) {
        pokerGameCollection.remove(pokerGame);
        EventLog.getInstance().logEvent(new Event(pokerGame.getDate()
                + " poker game removed from poker game collection"));
    }

    //EFFECTS: returns an unmodifiable list of poker games in this collection
    public List<PokerGame> getPokerGames() {
        return Collections.unmodifiableList(pokerGameCollection);
    }

    //EFFECTS: returns the number of poker games in the collection
    public int getNumPokerGames() {
        return pokerGameCollection.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pokerGames", pokerGamesToJson());
        return json;
    }

    //EFFECTS: returns things in this poker game collection as a JSON array
    private JSONArray pokerGamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PokerGame pokerGame : pokerGameCollection) {
            jsonArray.put(pokerGame.toJson());
        }

        return jsonArray;
    }
}
