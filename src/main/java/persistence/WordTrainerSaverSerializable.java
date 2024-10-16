package persistence;

import model.WordTrainer;

import java.io.*;

public class WordTrainerSaverSerializable implements WordTrainerSaver {
    @Override
    public void save(WordTrainer wordTrainer, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(wordTrainer);
            objectOutputStream.flush();
        }
    }

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
