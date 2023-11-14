package ui;

import model.PokerGameCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents application's main window frame.
 */
public class ApplicationGUI extends JFrame {
    private static final String welcomeMessage = "Welcome to PokerTrackr!";
    private PokerGameCollection pokerGameCollection;
    private PokerGameCollectionGUI pokerGameCollectionArea;
    private JLabel headerMessage;

    /**
     * Constructor sets up main menu.
     */
    public ApplicationGUI() {
        super("PokerTrackr");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        headerMessage = new JLabel(welcomeMessage);
        add(headerMessage, BorderLayout.NORTH);

        createStartingMenu();
        createPokerGameCollectionMenu();

        pack();
        setVisible(true);
    }

    //Create the starting menu GUI
    private void createStartingMenu() {
        headerMessage.setText("Caitlyn's Poker Game Collection");

    }

    // Create the poker game collection and poker game collection menu GUI
    private void createPokerGameCollectionMenu() {
        pokerGameCollection = new PokerGameCollection();
        pokerGameCollectionArea = new PokerGameCollectionGUI(this);
        add(pokerGameCollectionArea, BorderLayout.CENTER);
    }

    // starts the application
    public static void main(String[] args) {
        new ApplicationGUI();
    }
}
