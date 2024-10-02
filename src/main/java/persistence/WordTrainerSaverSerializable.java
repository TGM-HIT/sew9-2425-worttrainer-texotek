package persistence;

import model.WordTrainer;

import java.io.*;

public class WordTrainerSaverSerializable implements WordTrainerSaver {
    /**
     * Save the word trainer to a file
     * @param wordTrainer The WortTrainer Model to save
     * @param path The path to save the file
     * @throws IOException If something goes while writing the file
     */
    @Override
    public void save(WordTrainer wordTrainer, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(wordTrainer);
            objectOutputStream.flush();
        }
    }

    /**
     * Load the word trainer from a file
     * @param path The path to load the file
     * @return The WordTrainer Model
     * @throws IOException If something goes while reading the file
     */
    @Override
    public WordTrainer load(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (WordTrainer) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
