package ui;

import model.PokerGameCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's main window frame.
 */
public class ApplicationGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String welcomeMessage = "Welcome to PokerTrackr!";
    private PokerGameCollection pokerGameCollection;
    private PokerGameCollectionGUI pokerGameCollectionArea;
    private JPanel startMenuPanel;
    private JLabel headerMessage;
    private static final int LIST_ROW_COUNT = 30;
    private JList<String> list1 = new JList<>();
    private static final String JSON_STORE = "./data/pokergamecollection.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /**
     * Constructor sets up main menu.
     */
    public ApplicationGUI() {
        super("PokerTrackr");

        //setPreferredSize(new Dimension(WIDTH, HEIGHT));

        init();
        createStartingMenu();

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: initializes values
    private void init() {
        pokerGameCollection = new PokerGameCollection();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //Create the starting menu GUI
    private void createStartingMenu() {
        startMenuPanel = new JPanel();
        startMenuPanel.setLayout(new GridBagLayout());
        this.add(startMenuPanel);

        headerMessage = new JLabel(welcomeMessage);
        GridBagConstraints constraintsHeaderMessage = new GridBagConstraints();
        constraintsHeaderMessage.anchor = GridBagConstraints.NORTHWEST;

        ImageIcon gameIcon = new ImageIcon("data/PokerGameIconFinal copy.png");
        JLabel programImage = new JLabel(gameIcon);
        GridBagConstraints constraintsIcon = new GridBagConstraints();
        constraintsIcon.anchor = GridBagConstraints.NORTHEAST;

        startMenuPanel.add(headerMessage, constraintsHeaderMessage);
        startMenuPanel.add(programImage, constraintsIcon);

        //create and add buttons to startMenuPanel
        createNewPokerGameButton(startMenuPanel);
        createRemovePokerGameButton(startMenuPanel);
        createViewSelectedPokerGameButton(startMenuPanel);
        createSavePokerCollectionButton(startMenuPanel);
        createLoadPokerCollectionButton(startMenuPanel);


        //creates and adds empty poker game list to startMenuPanel
        createEmptyPokerGameList(startMenuPanel);
    }

    private void createNewPokerGameButton(JPanel panel) {
        JButton addNewPokerGame = new JButton("Add New Poker Game");
        GridBagConstraints constraintsAddNewPokerGame = makeButtonConstraints(2);
        addNewPokerGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stub
            }
        });
        panel.add(addNewPokerGame, constraintsAddNewPokerGame);
    }

    private void createRemovePokerGameButton(JPanel panel) {
        JButton removePokerGame = new JButton("Remove Poker Game");
        GridBagConstraints constraintsRemovePokerGame = makeButtonConstraints(3);
        removePokerGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stub
            }
        });
        panel.add(removePokerGame, constraintsRemovePokerGame);
    }

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

    private void createSavePokerCollectionButton(JPanel panel) {
        JButton savePokerCollection = new JButton("Save Poker Collection");
        GridBagConstraints constraintsSavePokerCollection = makeButtonConstraints(5);
        savePokerCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stub
            }
        });
        panel.add(savePokerCollection, constraintsSavePokerCollection);
    }

    private void createLoadPokerCollectionButton(JPanel panel) {
        JButton loadPokerCollection = new JButton("Load Poker Collection");
        GridBagConstraints constraintsLoadPokerCollection =  makeButtonConstraints(6);
        loadPokerCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stub
            }
        });
        panel.add(loadPokerCollection, constraintsLoadPokerCollection);
    }


    private GridBagConstraints makeButtonConstraints(int rowNumber) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = rowNumber;
        return constraints;
    }

    private void createEmptyPokerGameList(JPanel startMenuPanel) {
        JList<String> pokerGameJList = new JList<>();
        pokerGameJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pokerGameJList.setLayoutOrientation(JList.VERTICAL);
        pokerGameJList.setVisibleRowCount(LIST_ROW_COUNT);
        pokerGameJList.setPrototypeCellValue("00/AAA/0000");
        GridBagConstraints listConstraints = new GridBagConstraints();
        listConstraints.anchor = GridBagConstraints.LINE_START;

        JScrollPane scrollPane = new JScrollPane(pokerGameJList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 80));
        startMenuPanel.add(scrollPane, listConstraints);

        setLayout(new GridLayout(1, 2));

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(scrollPane);
        wrapperPanel.setBorder(BorderFactory.createTitledBorder("Poker Games:"));
        GridBagConstraints wrapperPanelConstraints = new GridBagConstraints();
        wrapperPanelConstraints.gridx = 0;
        wrapperPanelConstraints.gridheight = GridBagConstraints.REMAINDER;
        startMenuPanel.add(wrapperPanel, wrapperPanelConstraints);
    }

    // Create the poker game collection and poker game collection menu GUI
    private void createPokerGameCollectionMenu() {
        headerMessage.setText("Caitlyn's Poker Game Collection");
        pokerGameCollection = new PokerGameCollection();
        pokerGameCollectionArea = new PokerGameCollectionGUI(this);
        add(pokerGameCollectionArea, BorderLayout.CENTER);
    }

    // starts the application
    public static void main(String[] args) {
        new ApplicationGUI();
    }
}
