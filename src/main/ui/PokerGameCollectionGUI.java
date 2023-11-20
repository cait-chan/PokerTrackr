package ui;

import model.PokerGameCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Represents visual user interface for Poker Game Collection menu.
 */
public class PokerGameCollectionGUI extends JPanel {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 235;
    private ApplicationGUI applicationGUI;
    private static final String JSON_STORE = "./data/pokergamecollection.json";
    private JsonWriter jsonWriter;

    public PokerGameCollectionGUI(ApplicationGUI applicationGUI) {
        this.applicationGUI = applicationGUI;
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    private void addNewPokerGame() {
        //stub
    }

    private void removePokerGame() {
        //stub
    }

    private void viewSelectedPokerGame() {
        //stub
    }

//    private void savePokerGameCollection() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(pokerGameCollection);
//            jsonWriter.close();
//            //System.out.println("Saved your poker game collection to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            //System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }

}
