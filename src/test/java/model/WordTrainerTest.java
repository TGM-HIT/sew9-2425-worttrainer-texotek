package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordTrainerTest {
    private List<WordImagePair> pairs;
    @BeforeEach
    public void prepare() throws MalformedURLException {
        pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://pixnio.com/free-images/2017/09/26/2017-09-26-07-22-55.jpg")));
    }
    @Test
    public void testSelectNewPair() {
        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();

        assertNotNull(wordTrainer.getSelectedImageURL());
    }

    @Test
    public void testGuessSelectedWord() {
        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();
        assertTrue(wordTrainer.guessSelectedWord("cat"));
    }
}
