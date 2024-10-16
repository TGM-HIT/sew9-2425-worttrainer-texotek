package model;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordTrainer implements Serializable {
    @Serial
    final private static long serialVersionUID = 1L;
    final private List<WordImagePair> wordImagePairs;
    private int selectedIndex = -1;
    private int countTotalGuess = 0;
    private int countCorrectGuess = 0;
    private int countWrongGuess = 0;

    /**
     * Constructor for WordTrainer
     * @param pairs List of WordImagePair
     */
    public WordTrainer(List<WordImagePair> pairs) {
        this.wordImagePairs = pairs;
    }

    /**
     * Method to select a new pair randomly
     * @throws IllegalStateException If no pairs are present
     */
    public void selectNewPair() {
        int selectedBefore = selectedIndex;
        if(wordImagePairs.isEmpty())
            throw new IllegalStateException("No pairs present");

        while(selectedBefore == selectedIndex) {
            selectedIndex = (int) (Math.random() * wordImagePairs.size());
        }
    }

    /**
     * Method to select a pair at a specific index
     * @param index Index of the pair to select
     * @throws IllegalStateException If no pairs are present
     * @throws IndexOutOfBoundsException If index is out of bounds
     */
    public void selectNewPair(int index) {
        if(wordImagePairs.isEmpty()) {
            throw new IllegalStateException("No pairs present");
        }
        if(index < 0 || index >= wordImagePairs.size())
            throw new IndexOutOfBoundsException("Index out of bounds");

        selectedIndex = index;
    }

    /**
     * Method to get the selected image URL
     * @return URL of the selected image
     * @throws IllegalStateException If no word is selected
     */
    public URL getSelectedImageURL() throws IllegalStateException {
        if(selectedIndex == -1)
            throw new IllegalStateException("No word selected");

        return wordImagePairs.get(selectedIndex).getImageURL();
    }

    /**
     * Method to get the selected word
     * @return Word of the selected image
     * @throws IllegalStateException If no word is selected
     */
    public boolean guessSelectedWord(String word) throws IllegalStateException {
        if (selectedIndex == -1)
            throw new IllegalStateException("No word selected");

        countTotalGuess++;
        boolean isCorrect = wordImagePairs.get(selectedIndex).getWord().equals(word);

        if(isCorrect) {
            countCorrectGuess++;
            selectedIndex = -1;
        }
        else {
            countWrongGuess++;
        }

        return isCorrect;
    }

    /**
     * Method to get selected Word
     * @return Word of the selected Pair
     * @throws IllegalStateException If no word is selected
     */
    public String getSelectedWord() {
        if (selectedIndex == -1)
            throw new IllegalStateException("No word selected");

        return wordImagePairs.get(selectedIndex).getWord();
    }

    /**
     * Method to get the count of total guesses
     * @return Count of total guesses
     */
    public int getCountTotalGuess() {
        return countTotalGuess;
    }

    /**
     * Method to get the count of correct guesses
     * @return Count of correct guesses
     */
    public int getCountCorrectGuess() {
        return countCorrectGuess;
    }

    /**
     * Method to get the count of wrong guesses
     * @return Count of wrong guesses
     */
    public int getCountWrongGuess() {
        return countWrongGuess;
    }

    /**
     * Method to get the list of WordImagePairs
     * @return List of WordImagePairs
     */
    public List<WordImagePair> getWordImagePairs() {
        return wordImagePairs;
    }
}
