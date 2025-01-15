package controller;

import model.WordImagePair;
import model.WordTrainer;
import persistence.WordTrainerSaver;
import persistence.WordTrainerSaverJSON;
import persistence.WordTrainerSaverSerializable;
import view.WordTrainerFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Word Trainer application
 */
public class ApplicationController {
    private WordTrainer model;
    private final WordTrainerFrame view = new WordTrainerFrame(this);
    private final WordTrainerSaver wordTrainerSaver = new WordTrainerSaverJSON();

    /**
     * Constructor for ApplicationController
     * Loads the word trainer from a file
     */
    public ApplicationController() throws MalformedURLException {
        try {
            model = wordTrainerSaver.load("wordTrainer.json");
        } catch (IOException e) {
            e.printStackTrace();
            List<WordImagePair> pairs = new ArrayList<>();
            pairs.add(new WordImagePair("Apple", new URL("https://www.applesfromny.com/wp-content/uploads/2020/05/20Ounce_NYAS-Apples2.png")));
            pairs.add(new WordImagePair("Banana", new URL("https://images3.alphacoders.com/658/thumb-1920-658610.jpg")));
            pairs.add(new WordImagePair("Cherry", new URL("https://purepng.com/public/uploads/large/purepng.com-cherriescherrygenus-prunussweet-cherryornamental-cherrycherry-blossomcherries-1701527198162wctcb.png")));
            pairs.add(new WordImagePair("Cat", new URL("https://c.pxhere.com/photos/88/8a/cat_lying_blue_eye_small_ginger_fur_heal_pet_animal-609263.jpg!d")));
            pairs.add(new WordImagePair("Dog", new URL("https://get.pxhere.com/photo/puppy-dog-animal-cute-canine-pet-fur-mammal-hound-close-up-nose-snout-ears-vertebrate-beagle-resting-adorable-dog-breed-street-dog-dog-like-mammal-816169.jpg")));
            model = new WordTrainer(pairs);
            model.selectNewPair();
        }
    }

    /**
     * Method to start the Application
     */
    public void startApplication() {
        try {
            view.refreshImage(model.getSelectedImageURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        refreshStatistics();
        view.setVisible(true);
    }

    /**
     * Method to exit the application, it saves the word trainer to a file
     */
    public void exitApplication() {
        try {
            wordTrainerSaver.save(model, "wordTrainer.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        view.setVisible(false);
        System.exit(0);
    }

    /**
     * Method to create a new word trainer, if the guess is correct, select a new word.
     * @param guess The guess to check from the view
     */
    public void checkGuess(String guess) {
        if(model.guessSelectedWord(guess)) {
            JOptionPane.showMessageDialog(null, "Correct!");
            try {
                model.selectNewPair();
                view.refreshImage(model.getSelectedImageURL());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong!");
        }
        refreshStatistics();
    }
    /**
     * Refresh the statistics on the frame
     */
    private void refreshStatistics() {
        int total = model.getCountTotalGuess();
        int correct = model.getCountCorrectGuess();
        int wrong = model.getCountWrongGuess();

        view.refreshStatistics(total, correct, wrong);
    }

    public static void main(String[] args) throws IOException {
        ApplicationController controller = new ApplicationController();
        controller.startApplication();
    }
}
