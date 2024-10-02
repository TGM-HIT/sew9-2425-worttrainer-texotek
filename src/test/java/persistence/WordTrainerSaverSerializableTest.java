package persistence;

import model.WordImagePair;
import model.WordTrainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordTrainerSaverSerializableTest {
    private static final String TEST_FILE = "test.ser";
    private static WordTrainer originalWordTrainer;
    private static WordTrainer loadedWordTrainer;
    @BeforeAll
    static void setUp() throws IOException {
        List<WordImagePair> wordImagePairs = new ArrayList<>();
        wordImagePairs.add(new WordImagePair("cat", new URL("https://test1.com/test.jpg")));
        wordImagePairs.add(new WordImagePair("dog", new URL("https://test2.com/test.jpg")));

        originalWordTrainer = new WordTrainer(wordImagePairs);
        originalWordTrainer.selectNewPair();

        WordTrainerSaver wordTrainerSaver = new WordTrainerSaverSerializable();
        wordTrainerSaver.save(originalWordTrainer, TEST_FILE);

        File file = new File(TEST_FILE);
        assert file.exists();

        loadedWordTrainer = wordTrainerSaver.load(TEST_FILE);
    }
    @Test
    void testLoadedWordTrainerNotNull() {
        assertNotNull(loadedWordTrainer);
    }
    @Test
    void testLoadedWordTrainerSelectedWord() {
        assertEquals(originalWordTrainer.getSelectedWord(), loadedWordTrainer.getSelectedWord());
    }
    @Test
    void testLoadedWordTrainerSelectedImageURL() {
        assertEquals(originalWordTrainer.getSelectedImageURL(), loadedWordTrainer.getSelectedImageURL());
    }
    @Test
    void testLoadedWordTrainerWordImagePairsSize() {
        assertEquals(originalWordTrainer.getWordImagePairs().size(), loadedWordTrainer.getWordImagePairs().size());
    }

    @Test
    void testLoadedWordTrainerScore() {
        assertEquals(originalWordTrainer.getCountTotalGuess(), loadedWordTrainer.getCountTotalGuess());
        assertEquals(originalWordTrainer.getCountCorrectGuess(), loadedWordTrainer.getCountCorrectGuess());
        assertEquals(originalWordTrainer.getCountWrongGuess(), loadedWordTrainer.getCountWrongGuess());
    }
    @AfterAll
    static void cleanUp() {
        File file = new File(TEST_FILE);
        assert file.delete();
    }
}
