package ui;

import model.Player;
import model.PokerGame;
import model.PokerGameCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Poker game collection application
public class GameApp {
    private static final String JSON_STORE = "./data/pokergamecollection.json";
    private PokerGameCollection pokerGameCollection;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs poker game collection application
    public GameApp() throws FileNotFoundException {
        runPokerGameApp();
    }

    private void runPokerGameApp() {
        boolean keepRunning = true;
        String command;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nHave a good day!");
    }

    // EFFECTS: processes user input command
    private void processCommand(String command) {
        if (command.equals("n")) {
            createNewPokerGame();
        } else if (command.equals("v")) {
            viewAllPokerGames();
        } else if (command.equals("r")) {
            removeGame();
        } else if (command.equals("s")) {
            savePokerGameCollection();
        } else if (command.equals("l")) {
            loadPokerGameCollection();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: initializes values
    private void init() {
        pokerGameCollection = new PokerGameCollection();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> new poker game");
        System.out.println("\tv -> view all poker games");
        System.out.println("\tr -> remove poker game");
        System.out.println("\ts -> save poker collection to file");
        System.out.println("\tl -> load poker collection from file");
        System.out.println("\tq -> quit");
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates a new poker game and adds it to the collection
    //          adds players to new poker game along with buy-ins and cash-outs
    private void createNewPokerGame() {
        System.out.print("Enter date for poker game (dd/MMM/yyyy): ");
        String date = input.next();
        PokerGame pokerGame = new PokerGame(date);
        pokerGameCollection.addPokerGame(pokerGame);
        newGameAddPlayers(pokerGame);
        if (pokerGame.getPlayers().size() > 0) {
            newGameAddBuyInsAndCashOuts(pokerGame);
        }
    }

    // REQUIRES: pokerGameCollection.size() > 0
    // MODIFIES: this
    // EFFECTS: adds players to new poker game
    private void newGameAddPlayers(PokerGame pokerGame) {
        boolean addingPlayers = true;
        String command = null;

        while (addingPlayers) {
            System.out.print("Would you like to add a player? (y/n) ");
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("y")) {
                System.out.print("Enter player name: ");
                pokerGame.addPlayer(new Player(input.next()));
            } else if (command.equals("n")) {
                System.out.println("Players in this poker game: ");
                for (Player player : pokerGame.getPlayers()) {
                    System.out.println(player.getName());
                }
                addingPlayers = false;
            } else {
                System.out.println("Input invalid, please enter y or n.");
            }
        }
    }

    // REQUIRES: pokerGameCollection.size() > 0
    // MODIFIES: this
    // EFFECTS: prompts user for player buy-in and cash-out values
    private void newGameAddBuyInsAndCashOuts(PokerGame pokerGame) {
        for (Player player : pokerGame.getPlayers()) {                //list players and prompt user for buy-ins
            System.out.print("Please enter buy-in amount for player (CAD dollars): ");
            int buyInAmount = Integer.parseInt(input.next());
            pokerGame.addBuyIn(buyInAmount);
        }
        for (int i = 0; i < pokerGame.getPlayers().size(); i++) {                //list players and buy-ins, prompt user
            System.out.println("Player: " + pokerGame.getPlayers().get(i).getName()); //for cash-outs
            System.out.println("Buy-in: " + pokerGame.getBuyIns().get(i));
            System.out.print("Please enter cash-out amount for this player (CAD dollars): ");
            int cashOutAmount = Integer.parseInt(input.next());
            pokerGame.addCashOut(cashOutAmount);
        }
    }


    // EFFECTS: displays all the poker games in the collection and allows user to select a game
    public void viewAllPokerGames() {
        if (pokerGameCollection.getNumPokerGames() == 0) {
            System.out.println("No games in poker collection.");
        } else {
            System.out.println("Poker games in your collection: ");
            for (PokerGame game : pokerGameCollection.getPokerGames()) {
                System.out.println(game.getDate());
            }
            System.out.println("Would you like to view a poker game? (y/n)");
            String answer = input.next();
            if (answer.equals("y")) {
                viewPokerGame();
            } else if (answer.equals("n")) {
                System.out.println("Returning to main menu.");
            } else {
                System.out.println("Input invalid. Returning to main menu.");
            }
        }
    }

    // REQUIRES: pokerGameCollection.size() > 0
    // EFFECTS: selects poker game with given date for user to view game options
    private void viewPokerGame() {
        System.out.print("Please enter the date of the game you want to view (dd/MMM/yyyy): ");
        String date = input.next();
        PokerGame pokerGame = getPokerGame(date);
        boolean keepRunning = true;
        String command = null;

        while (keepRunning) {
            selectGameMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processGameMenuCommand(command, pokerGame);
            }
        }
        System.out.println("Returning to main menu.");
    }

    //EFFECTS: saves the poker game collection to file
    private void savePokerGameCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(pokerGameCollection);
            jsonWriter.close();
            System.out.println("Saved your poker game collection to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads poker game collection from file
    private void loadPokerGameCollection() {
        try {
            pokerGameCollection = jsonReader.read();
            System.out.println("Loaded your poker game collection from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays poker game menu options
    private void selectGameMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add player to poker game");
        System.out.println("\tr -> remove player from poker game");
        System.out.println("\tv -> view players and statistics in poker game");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: processes user input command
    private void processGameMenuCommand(String command, PokerGame pokerGame) {
        if (command.equals("a")) {
            existingGameAddPlayer(pokerGame);
        } else if (command.equals("r")) {
            existingGameRemovePlayer(pokerGame);
        } else if (command.equals("v")) {
            viewPlayersAndStatistics(pokerGame);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a single player to a poker game along with their buy-in and cash-out values
    private void existingGameAddPlayer(PokerGame pokerGame) {
        displayPlayers(pokerGame);
        System.out.print("Who would you like to add? ");
        String playerToAdd = input.next();
        if (!pokerGame.getPlayers().contains(playerToAdd)) {   //if player not already in game, add them
            pokerGame.addPlayer(new Player(playerToAdd));
            System.out.print("Please enter buy-in amount for player (CAD dollars): ");
            int buyInAmount = Integer.parseInt(input.next());
            pokerGame.addBuyIn(buyInAmount);
            System.out.print("Please enter cash-out amount for player (CAD dollars): ");
            int cashOutAmount = Integer.parseInt(input.next());
            pokerGame.addCashOut(cashOutAmount);
        } else {
            System.out.print("Player already in this poker game.");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a single player from given poker game
    private void existingGameRemovePlayer(PokerGame pokerGame) {
        displayPlayers(pokerGame);
        System.out.print("Who would you like to remove? ");
        String playerToRemove = input.next();
        if (pokerGame.getPlayers().contains(playerToRemove)) {            //if player is in game
            for (int i = 0; i < pokerGame.getPlayers().size(); i++) {
                Player player = pokerGame.getPlayers().get(i);
                int buyIn = pokerGame.getBuyIns().get(i);
                int cashOut = pokerGame.getCashOuts().get(i);
                if (player.getName().equals(playerToRemove)) {
                    pokerGame.removePlayer(player, buyIn, cashOut);   //remove player, buy-in and cash-out values
                                                                      // from player level
                    pokerGame.getBuyIns().remove(i);                  //remove their buy-in from poker game
                    pokerGame.getCashOuts().remove(i);                //remove their cash-out from poker game
                }
            }
        } else {
            System.out.print("Player is not in this poker game.");
        }
    }

    // REQUIRES: pokerGame.getPlayers().size() > 0
    // MODIFIES: this
    // EFFECTS: prints out player buy-in, cash-out and earnings values
    private void viewPlayersAndStatistics(PokerGame pokerGame) {
        for (int i = 0; i < pokerGame.getPlayers().size(); i++) {
            Player player = pokerGame.getPlayers().get(i);
            int buyIn = pokerGame.getBuyIns().get(i);
            int cashOut = pokerGame.getCashOuts().get(i);
            int earnings = cashOut - buyIn;
            System.out.print("Player: " + player.getName() + " ");
            System.out.print("Buy-in: " + buyIn + " ");
            System.out.print("Cash-out: " + cashOut + " ");
            System.out.print("Earnings: " + earnings + " \n");

        }
    }


    // REQUIRES: pokerGameCollection.size() > 0
    // MODIFIES: this
    // EFFECTS: removes game from PokerGameCollection
    private void removeGame() {
        if (pokerGameCollection.getNumPokerGames() > 0) {
            System.out.println("Poker games in this collection: ");
            for (PokerGame pokerGame : pokerGameCollection.getPokerGames()) {
                System.out.println(pokerGame.getDate());
            }
            System.out.print("Which game would you like to remove (dd/MMM/yyyy)? ");
            String date = input.next();
            PokerGame pokerGame = getPokerGame(date);
            if (pokerGameCollection.getPokerGames().contains(pokerGame)) {            //if game is in collection
                removeAllPlayers(pokerGame);                          //remove players and update fields
                pokerGameCollection.removePokerGame(pokerGame);                //remove poker game from collection
            } else {
                System.out.print("Poker game is not in this poker game collection.");
            }
        } else {
            System.out.println("PokerGameCollection is empty.");
        }
    }

    // MODIFIES: poker game
    // EFFECTS: removes all players from poker game
    private void removeAllPlayers(PokerGame pokerGame) {
        for (int i = 0; i < pokerGame.getPlayers().size(); i++) {
            Player player = pokerGame.getPlayers().get(i);
            int buyIn = pokerGame.getBuyIns().get(i);
            int cashOut = pokerGame.getCashOuts().get(i);
            pokerGame.removePlayer(player, buyIn, cashOut);   //remove player, buy-in and cash-out values
            // from player level
            pokerGame.getBuyIns().remove(i);                  //remove their buy-in from poker game
            pokerGame.getCashOuts().remove(i);                //remove their cash-out from poker game
        }
    }

    // EFFECTS: returns poker game with given date
    private PokerGame getPokerGame(String date) {
        PokerGame pokerGame = null;
        for (PokerGame game : pokerGameCollection.getPokerGames()) {
            if (game.getDate().equals(date)) {
                return game;
            }
        }
        return pokerGame;
    }

    // EFFECTS: displays all players in given poker game
    private void displayPlayers(PokerGame pokerGame) {
        System.out.println("Players in this game: ");
        for (Player player : pokerGame.getPlayers()) {
            System.out.println(player.getName());
        }
    }
}
