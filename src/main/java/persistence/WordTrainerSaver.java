package persistence;

import model.WordTrainer;

import java.io.IOException;

public interface WordTrainerSaver {
    void save(WordTrainer wordTrainer, String path) throws IOException;
    WordTrainer load(String path) throws IOException;
}
