package persistence;

import model.WordTrainer;

import java.io.IOException;

public interface WordTrainerSaver {
    /**
     * Save the word trainer to a file
     * @param wordTrainer The WortTrainer Model to save
     * @param path The path to save the file
     * @throws IOException If something goes while writing the file
     */
    void save(WordTrainer wordTrainer, String path) throws IOException;
    /**
     * Load the word trainer from a file
     * @param path The path to load the file
     * @return The WordTrainer Model
     * @throws IOException If something goes while reading the file
     */
    WordTrainer load(String path) throws IOException;
}
