package controller;

import model.WordImagePair;
import model.WordTrainer;
import persistence.WordTrainerSaver;
import persistence.WordTrainerSaverSerializable;
import view.WordTrainerFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Word Trainer application
 */
public class ApplicationController {
    private WordTrainer model;
    private WordTrainerFrame view = new WordTrainerFrame(this);

    /**
     * Constructor for ApplicationController
     * Loads the word trainer from a file and displays the first image
     */
    public ApplicationController() {
        WordTrainerSaver wordTrainerSaver = new WordTrainerSaverSerializable();
        try {
            model = wordTrainerSaver.load("wordTrainer.ser");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not load word trainer");
        }

        try {
            view.refreshImage(model.getSelectedImageURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        view.setVisible(true);
    }

    /**
     * Method to create a new word trainer, if the guess is correct, select a new word.
     * @param guess The guess to check from the view
     */
    public void checkGuess(String guess) {
        if(model.getSelectedWord().equals(guess)) {
            JOptionPane.showMessageDialog(null, "Correct!");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong!");
        }
        try {
            model.selectNewPair();
            view.refreshImage(model.getSelectedImageURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
        ApplicationController controller = new ApplicationController();
    }
}
