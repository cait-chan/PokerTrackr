package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a player having a name, lifetime buy-ins (in CAD dollars), lifetime cash-outs (in CAD dollars),
// number of games won and lost
public class Player implements Writable {
    private String name;
    private int totalBuyIns;
    private int totalCashOuts;
    private int gamesWon;
    private int gamesLost;

    // REQUIRES: name has a non-zero length
    // EFFECTS: creates a player with given name, zero lifetimeBuyIns and zero games won and lost
    public Player(String name) {
        this.name = name;
        this.totalBuyIns = 0;
        this.totalCashOuts = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
    }

    // REQUIRES: amount >= 0
    // EFFECTS: adds amount to totalBuyIns for player
    public void increaseTotalBuyIns(int amount) {
        totalBuyIns += amount;
    }

    // REQUIRES: amount >= 0
    // EFFECTS: adds amount to totalCashOuts for player
    public void increaseTotalCashOuts(int amount) {
        totalCashOuts += amount;
    }

    // REQUIRES: amount >= 0
    // EFFECTS: subtracts amount to totalBuyIns for player
    public void decreaseTotalBuyIns(int amount) {
        totalBuyIns += amount;
    }

    // REQUIRES: amount >= 0
    // EFFECTS: subtracts amount to totalCashOuts for player
    public void decreaseTotalCashOuts(int amount) {
        totalCashOuts += amount;
    }

    // EFFECTS: returns player name
    public String getName() {
        return name;
    }

    // EFFECTS: returns lifetime buy in amount in CAD dollars for player
    public int getTotalBuyIns() {
        return totalBuyIns;
    }

    // EFFECTS: returns lifetime cash out amount in CAD dollars for player
    public int getTotalCashOuts() {
        return totalCashOuts;
    }

    // EFFECTS: returns number of games won for player
    public int getGamesWon() {
        return gamesWon;
    }

    // EFFECTS: returns number of games lost for player
    public int getGamesLost() {
        return gamesLost;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("total buy-ins", totalBuyIns);
        json.put("total cash-outs", totalCashOuts);
        json.put("games won", gamesWon);
        json.put("games lost", gamesLost);
        return json;
    }
}
