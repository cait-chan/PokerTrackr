package ui;

import model.Event;
import model.EventLog;
import model.PokerGame;
import model.PokerGameCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Represents application's main window frame.
public class ApplicationGUI extends JFrame {
    private static final String welcomeMessage = "Welcome to PokerTrackr!";
    private PokerGameCollection pokerGameCollection;
    private JPanel startMenuPanel;
    private JLabel headerMessage;
    private static final int LIST_ROW_COUNT = 10;
    private DefaultListModel<String> pokerGameListModel;
    private JList<String> pokerGameJList;
    private JPanel wrapperPanel;
    private static final String JSON_STORE = "./data/pokergamecollection.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: sets up application
    public ApplicationGUI() throws FileNotFoundException {
        super("PokerTrackr");

        //setPreferredSize(new Dimension(WIDTH, HEIGHT));

        init();
        createStartingMenu();

        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            }
        });
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: initializes values
    private void init() {
        pokerGameCollection = new PokerGameCollection();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Creates the starting menu GUI
    private void createStartingMenu() {
        startMenuPanel = new JPanel();
        startMenuPanel.setLayout(new GridBagLayout());
        this.add(startMenuPanel);

        headerMessage = new JLabel(welcomeMessage);
        GridBagConstraints constraintsHeaderMessage = new GridBagConstraints();
        constraintsHeaderMessage.anchor = GridBagConstraints.NORTHWEST;
        startMenuPanel.add(headerMessage, constraintsHeaderMessage);

        JLabel programImage = new JLabel(new ImageIcon("data/PokerGameIconFinal copy.png"));
        GridBagConstraints constraintsIcon = new GridBagConstraints();
        constraintsIcon.anchor = GridBagConstraints.NORTHEAST;
        startMenuPanel.add(programImage, constraintsIcon);

        //create and add buttons to startMenuPanel
        createNewPokerGameButton(startMenuPanel);
        createRemovePokerGameButton(startMenuPanel);
        createViewSelectedPokerGameButton(startMenuPanel);
        createSavePokerCollectionButton(startMenuPanel);
        createLoadPokerCollectionButton(startMenuPanel);

        //creates and adds empty poker game list to startMenuPanel
        createPokerGameList(startMenuPanel);
    }

    // EFFECTS: makes new poker game button to add a new poker game to collection
    private void createNewPokerGameButton(JPanel panel) {
        JButton addNewPokerGame = new JButton("Add New Poker Game");
        GridBagConstraints constraintsAddNewPokerGame = makeButtonConstraints(2);
        addNewPokerGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addNewPokerGame) {
                    newPokerGameGUI();
                }
            }
        });
        panel.add(addNewPokerGame, constraintsAddNewPokerGame);
    }

    // EFFECTS: makes remove poker game button to remove selected game from poker game list
    private void createRemovePokerGameButton(JPanel panel) {
        JButton removePokerGame = new JButton("Remove Poker Game");
        GridBagConstraints constraintsRemovePokerGame = makeButtonConstraints(3);
        removePokerGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = pokerGameJList.getSelectedIndex();
                pokerGameListModel.remove(index);
                pokerGameCollection.removePokerGame(pokerGameCollection.getPokerGames().get(index));
            }
        });
        panel.add(removePokerGame, constraintsRemovePokerGame);
    }


    // EFFECTS: makes view selected poker game button to view details of selected game from list
    private void createViewSelectedPokerGameButton(JPanel panel) {
        JButton viewSelectedPokerGame = new JButton("View Selected Poker Game");
        GridBagConstraints constraintsViewPokerGame = makeButtonConstraints(4);
        viewSelectedPokerGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stub
            }
        });
        panel.add(viewSelectedPokerGame, constraintsViewPokerGame);
    }

    // EFFECTS: makes save poker collection button to save data
    private void createSavePokerCollectionButton(JPanel panel) {
        JButton savePokerCollection = new JButton("Save Poker Collection");
        GridBagConstraints constraintsSavePokerCollection = makeButtonConstraints(5);
        savePokerCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(pokerGameCollection);
                    jsonWriter.close();
                    headerMessage.setText("Poker Collection was saved.");
                } catch (FileNotFoundException ex) {
                    headerMessage.setText("Unable to save Poker Collection");
                }
            }
        });
        panel.add(savePokerCollection, constraintsSavePokerCollection);
    }

    // EFFECTS: makes load poker collection button to load data
    private void createLoadPokerCollectionButton(JPanel panel) {
        JButton loadPokerCollection = new JButton("Load Poker Collection");
        GridBagConstraints constraintsLoadPokerCollection =  makeButtonConstraints(6);
        loadPokerCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pokerGameCollection = jsonReader.read();
                    panel.remove(wrapperPanel);
                    createPokerGameList(panel);
                    headerMessage.setText("Poker Collection was loaded.");
                } catch (IOException ex) {
                    headerMessage.setText("Unable to load Poker Collection.");
                }
            }
        });
        panel.add(loadPokerCollection, constraintsLoadPokerCollection);
    }


    // EFFECTS: standardizes button constraints
    private GridBagConstraints makeButtonConstraints(int rowNumber) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = rowNumber;
        return constraints;
    }

    // EFFECTS: creates poker game list on left side of menu
    private void createPokerGameList(JPanel startMenuPanel) {
        pokerGameListModel = new DefaultListModel();
        for (PokerGame pokerGame : pokerGameCollection.getPokerGames()) {
            pokerGameListModel.addElement(pokerGame.getDate());
        }

        pokerGameJList = new JList<>(pokerGameListModel);
        pokerGameJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pokerGameJList.setLayoutOrientation(JList.VERTICAL);
        pokerGameJList.setVisibleRowCount(LIST_ROW_COUNT);
        pokerGameJList.setPrototypeCellValue("00/AAA/0000");
        GridBagConstraints listConstraints = new GridBagConstraints();

        JScrollPane scrollPane = new JScrollPane(pokerGameJList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 120));
        startMenuPanel.add(scrollPane, listConstraints);

        wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(scrollPane);
        wrapperPanel.setBorder(BorderFactory.createTitledBorder("Poker Games:"));
        GridBagConstraints wrapperPanelConstraints = new GridBagConstraints();
        wrapperPanelConstraints.gridx = 0;
        wrapperPanelConstraints.gridheight = GridBagConstraints.REMAINDER;
        startMenuPanel.add(wrapperPanel, wrapperPanelConstraints);
    }

    // EFFECTS: makes new poker game input area
    private void newPokerGameGUI() {
        new PokerGameGUI(this);
        //revalidate();
        repaint();
    }

    // EFFECTS: gets pokerGameCollection
    public PokerGameCollection getPokerGameCollection() {
        return pokerGameCollection;
    }

    // EFFECTS: gets pokerGameListModel
    public DefaultListModel<String> getPokerGameListModel() {
        return pokerGameListModel;
    }


    // EFFECTS: starts the application
    public static void main(String[] args) {
        try {
            new ApplicationGUI();
        } catch (FileNotFoundException e) {
            //if app not found, create JFrame with JLabel with error message
            JFrame noFileException = new JFrame();
            JLabel errorMessage = new JLabel("Unable to run application: file not found");
            noFileException.add(errorMessage);
        }
    }
}
