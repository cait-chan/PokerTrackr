package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a poker game with a date, a list of players and their buy-in amounts
public class PokerGame implements Writable {
    private final String date;
    private List<Player> players;    //list of players participating in this poker game
    private List<Integer> buyIns;    //list of buy in values which correlate to the same index as players list
    private List<Integer> cashOuts;  //list of cash out values which correlate to the same index as players list

    // REQUIRES:
    // EFFECTS: creates a new poker game with given date and empty lists of players, buy-ins and cash-outs
    public PokerGame(String date) {
        this.date = date;
        players = new ArrayList<>();
        buyIns = new ArrayList<>();
        cashOuts = new ArrayList<>();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds given player to the list of players
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: removes given player from the list of players
    public void removePlayer(Player player, int buyIn, int cashOut) {
        this.players.remove(player);
        player.decreaseTotalBuyIns(buyIn);
        player.decreaseTotalCashOuts(cashOut);
        for (int i = 0; i < getBuyIns().size(); i++) {
            if (getBuyIns().get(i) == buyIn) {
                this.buyIns.remove(getBuyIns().get(i));
            }
        }
        for (int i = 0; i < getCashOuts().size(); i++) {
            if (getCashOuts().get(i) == cashOut) {
                this.cashOuts.remove(getCashOuts().get(i));
            }
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds buy-in amount to buyIns list
    public void addBuyIn(int amount) {
        buyIns.add(amount);
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds cash-out amount to cashOuts list
    public void addCashOut(int amount) {
        cashOuts.add(amount);
    }

    // EFFECTS: returns list of players in game
    public List<Player> getPlayers() {
        return players;
    }

    // EFFECTS: returns list of buy-ins for game
    public List<Integer> getBuyIns() {
        return buyIns;
    }

    // EFFECTS: returns list of cash-outs for game
    public List<Integer> getCashOuts() {
        return cashOuts;
    }

    // EFFECTS: returns date of poker game
    public String getDate() {
        return date;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("players", playersToJson());
        json.put("buy-ins", buyIns);
        json.put("cash-outs", cashOuts);
        return json;
    }

    //EFFECTS: returns players in this poker game as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player player : players) {
            jsonArray.put(player.toJson());
        }

        return jsonArray;
    }
}
