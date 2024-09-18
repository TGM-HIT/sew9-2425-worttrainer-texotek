package model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordTrainer {
    final private List<WordImagePair> wordImagePairs;
    private int selectedIndex = - 1;
    private int countTotalGuess;
    private int countCorrectGuess;
    private int countWrongGuess;

    public WordTrainer(List<WordImagePair> pairs) {
        this.wordImagePairs = pairs;
    }
    public void selectNewPair() {
        if(wordImagePairs.isEmpty())
            return;

        selectedIndex = (int) (Math.random() * wordImagePairs.size());
    }
    public void selectNewPair(int index) {
        if(wordImagePairs.isEmpty() || index < 0 || index >= wordImagePairs.size())
            return;

        selectedIndex = index;
    }

    public URL getSelectedImageURL() throws IllegalStateException {
        if(selectedIndex == -1)
            throw new IllegalStateException("No word selected");

        return wordImagePairs.get(selectedIndex).getImageURL();
    }
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
}
