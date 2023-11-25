package ui;

import model.Player;
import model.PokerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//Represents visual user interface for Poker Game menu.
public class PokerGameGUI extends JFrame {
    private ApplicationGUI applicationGUI;
    private PokerGame pokerGame;
    private JPanel pokerGamePanel;
    private DefaultListModel<String> playerListModel;
    private JList<String> playerJList;
    private JPanel listPanel;

    // REQUIRES:
    // MODIFIES: this, applicationGUI
    // EFFECTS: creates a new poker game and adds it to the collection
    //          adds players to new poker game along with buy-ins and cash-outs
    public PokerGameGUI(ApplicationGUI applicationGUI) {
        super("New Poker Game");

        String date = JOptionPane.showInputDialog("Date (DD/MMM/YYYY): ");

        pokerGame = new PokerGame(date);
        applicationGUI.getPokerGameCollection().addPokerGame(pokerGame);
        applicationGUI.getPokerGameListModel().addElement(pokerGame.getDate());

        createPokerGameMenu(date);
        pack();
        setVisible(true);
    }

    // EFFECTS: Creates the poker game menu GUI
    private void createPokerGameMenu(String date) {
        pokerGamePanel = new JPanel();
        pokerGamePanel.setLayout(new GridBagLayout());
        this.add(pokerGamePanel);

        JLabel dateLabel = new JLabel("Date: " + date);
        GridBagConstraints constraintsHeaderMessage = new GridBagConstraints();
        constraintsHeaderMessage.anchor = GridBagConstraints.NORTHWEST;
        pokerGamePanel.add(dateLabel, constraintsHeaderMessage);

        //create and add buttons to pokerGamePanel
        createNewPlayerButton(pokerGamePanel);
        createRemovePlayerButton(pokerGamePanel);
        createViewSelectedPlayerButton(pokerGamePanel);
        //createReturnToMainMenuButton(pokerGamePanel);

        //creates and adds poker game player list to startMenuPanel
        createPlayerList(pokerGamePanel);
    }

    // EFFECTS: makes new player button to add a new player to the poker game
    private void createNewPlayerButton(JPanel panel) {
        JButton addNewPlayer = new JButton("Add New Player");
        GridBagConstraints constraintsAddNewPlayer = makeButtonConstraints(2);
        addNewPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayers(pokerGame);
            }
        });
        panel.add(addNewPlayer, constraintsAddNewPlayer);
    }

    // EFFECTS: makes remove player button to remove selected player from player list
    private void createRemovePlayerButton(JPanel panel) {
        JButton removePlayer = new JButton("Remove Player");
        GridBagConstraints constraintsRemovePlayer = makeButtonConstraints(3);
        removePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = playerJList.getSelectedIndex();
                playerListModel.remove(index);
            }
        });
        panel.add(removePlayer, constraintsRemovePlayer);
    }


    // EFFECTS: makes view selected player button to view details of selected player from list
    private void createViewSelectedPlayerButton(JPanel panel) {
        JButton viewSelectedPlayer = new JButton("View Selected Player");
        GridBagConstraints constraintsViewPlayer = makeButtonConstraints(4);
        viewSelectedPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stub
            }
        });
        panel.add(viewSelectedPlayer, constraintsViewPlayer);
    }

//    // EFFECTS: makes save poker collection button to save data
//    private void createReturnToMainMenuButton(JPanel panel) {
//        JButton returnToMainMenu = new JButton("Return to Main Menu");
//        GridBagConstraints constraintsReturnMenu = makeButtonConstraints(5);
//        returnToMainMenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                PokerGameGUI.dispose();
//            }
//        });
//        panel.add(returnToMainMenu, constraintsReturnMenu);
//    }

    // EFFECTS: standardizes button constraints
    private GridBagConstraints makeButtonConstraints(int rowNumber) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = rowNumber;
        return constraints;
    }

    // EFFECTS: creates player list on left side of menu
    private void createPlayerList(JPanel pokerGamePanel) {
        playerListModel = new DefaultListModel();
        for (Player player : pokerGame.getPlayers()) {
            playerListModel.addElement(player.getName());
        }

        playerJList = new JList<>(playerListModel);
        playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerJList.setLayoutOrientation(JList.VERTICAL);
        playerJList.setVisibleRowCount(3);
        playerJList.setPrototypeCellValue("Abc");
        GridBagConstraints listConstraints = new GridBagConstraints();

        JScrollPane scrollPane = new JScrollPane(playerJList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 60));
        pokerGamePanel.add(scrollPane, listConstraints);

        listPanel = new JPanel(new BorderLayout());
        listPanel.add(scrollPane);
        listPanel.setBorder(BorderFactory.createTitledBorder("Players:"));
        GridBagConstraints wrapperPanelConstraints = new GridBagConstraints();
        wrapperPanelConstraints.gridx = 0;
        wrapperPanelConstraints.gridheight = GridBagConstraints.REMAINDER;
        pokerGamePanel.add(listPanel, wrapperPanelConstraints);
    }

    // REQUIRES: pokerGameCollection.size() > 0
    // MODIFIES: this
    // EFFECTS: adds players to new poker game
    private void addPlayers(PokerGame pokerGame) {
        boolean addingPlayers = true;

        JTextField nameField = new JTextField(5);
        JTextField buyInField = new JTextField(5);
        JTextField cashOutField = new JTextField(5);

        JPanel playerPanel = new JPanel();
        playerPanel.add(new JLabel("Name: "));
        playerPanel.add(nameField);
        playerPanel.add(new JLabel("Buy In: "));
        playerPanel.add(buyInField);
        playerPanel.add(new JLabel("Cash Out: "));
        playerPanel.add(cashOutField);

        int result = JOptionPane.showConfirmDialog(null, playerPanel,
                "Please enter player details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int buyIn = Integer.valueOf(buyInField.getText());
            int cashOut = Integer.valueOf(cashOutField.getText());

            Player player = new Player(name);
            player.increaseTotalBuyIns(buyIn);
            player.increaseTotalCashOuts(cashOut);

            pokerGame.addPlayer(new Player(nameField.getText()));
            pokerGame.addBuyIn(buyIn);
            pokerGame.addCashOut(cashOut);

            playerListModel.addElement(name);
        }
    }
}
