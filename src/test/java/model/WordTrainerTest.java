package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordTrainerTest {
    @Test
    public void testSelectNewPairRandom() throws MalformedURLException {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://test.de/1.jpg")));

        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();

        assertNotNull(wordTrainer.getSelectedImageURL());
    }
    @Test
    public void testSelectNewPairRandomEmpty() {
        List<WordImagePair> pairs = new ArrayList<>();
        WordTrainer wordTrainer = new WordTrainer(pairs);
        assertThrows(IllegalStateException.class, wordTrainer::selectNewPair);
    }

    @Test
    public void testSelectedNewPairIndex() throws MalformedURLException {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://test.de/1.jpg")));
        pairs.add(new WordImagePair("dog", new URL("https://test.de/2.jpg")));
        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair(1);
        assertEquals("dog", wordTrainer.getSelectedWord());
    }

    @Test
    public void testGuessSelectedWord() throws MalformedURLException {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://test.de/1.jpg")));
        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();
        assertTrue(wordTrainer.guessSelectedWord("cat"));
    }

    @Test
    public void testGuessSelectedWordWrong() throws MalformedURLException {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://test.de/1.jpg")));
        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();
        assertFalse(wordTrainer.guessSelectedWord("dog"));
    }

    @Test
    public void testGuessSelectedWordEmpty() {
        List<WordImagePair> pairs = new ArrayList<>();
        WordTrainer wordTrainer = new WordTrainer(pairs);
        assertThrows(IllegalStateException.class, () -> wordTrainer.guessSelectedWord("dog"));
    }

    @Test
    public void testGuessSelectedWordCount() throws MalformedURLException {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://test.de/1.jpg")));
        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();
        wordTrainer.guessSelectedWord("cat");
        assertEquals(1, wordTrainer.getCountTotalGuess());
        assertEquals(1, wordTrainer.getCountCorrectGuess());
        assertEquals(0, wordTrainer.getCountWrongGuess());
    }

    @Test
    public void testGuessSelectedWordCountWrong() throws MalformedURLException {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://test.de/1.jpg")));
        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();
        wordTrainer.guessSelectedWord("dog");
        assertEquals(1, wordTrainer.getCountTotalGuess());
        assertEquals(0, wordTrainer.getCountCorrectGuess());
        assertEquals(1, wordTrainer.getCountWrongGuess());
    }
}
