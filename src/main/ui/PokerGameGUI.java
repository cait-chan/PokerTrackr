package ui;

import model.Player;
import model.PokerGame;

import javax.swing.*;

//Represents visual user interface for Poker Game menu.
public class PokerGameGUI extends JOptionPane {
    private ApplicationGUI applicationGUI;

    // REQUIRES:
    // MODIFIES: this, applicationGUI
    // EFFECTS: creates a new poker game and adds it to the collection
    //          adds players to new poker game along with buy-ins and cash-outs
    public PokerGameGUI(ApplicationGUI applicationGUI) {
        String date = JOptionPane.showInputDialog("Date (DD/MMM/YYYY): ");
        PokerGame pokerGame = new PokerGame(date);
        applicationGUI.getPokerGameCollection().addPokerGame(pokerGame);
        applicationGUI.getPokerGameListModel().addElement(pokerGame.getDate());
        //newGameAddPlayers(pokerGame);
//        if (pokerGame.getPlayers().size() > 0) {
//            newGameAddBuyInsAndCashOuts(pokerGame);
//        }
        //applicationGUI.getPokerGameJList().addElement(pokerGame);
    }

    // REQUIRES: pokerGameCollection.size() > 0
    // MODIFIES: this
    // EFFECTS: adds players to new poker game
    private void newGameAddPlayers(PokerGame pokerGame) {
        boolean addingPlayers = true;

        while (addingPlayers) {
            int addPlayerAnswer = JOptionPane.showConfirmDialog(null,
                    "Would you like to add a player?", "New Player", JOptionPane.YES_NO_OPTION);

            if (addPlayerAnswer == 1) {
                String name = JOptionPane.showInputDialog("Enter player name: ");
                pokerGame.addPlayer(new Player(name));
            } else if (addPlayerAnswer == 0) {
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
//    private void newGameAddBuyInsAndCashOuts(PokerGame pokerGame) {
//        for (Player player : pokerGame.getPlayers()) {                //list players and prompt user for buy-ins
//            System.out.print("Please enter buy-in amount for player (CAD dollars): ");
//            int buyInAmount = Integer.parseInt(input.next());
//            pokerGame.addBuyIn(buyInAmount);
//        }
//        for (int i = 0; i < pokerGame.getPlayers().size(); i++) {                //list players and buy-ins, prompt user
//            System.out.println("Player: " + pokerGame.getPlayers().get(i).getName()); //for cash-outs
//            System.out.println("Buy-in: " + pokerGame.getBuyIns().get(i));
//            System.out.print("Please enter cash-out amount for this player (CAD dollars): ");
//            int cashOutAmount = Integer.parseInt(input.next());
//            pokerGame.addCashOut(cashOutAmount);
//        }
//    }
}
