package persistence;

import java.io.File;
import java.io.IOException;

import model.WordTrainer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WordTrainerSaverJSON implements WordTrainerSaver {

    @Override
    public void save(WordTrainer wordTrainer, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), wordTrainer);
    }

    @Override
    public WordTrainer load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), WordTrainer.class);
    }
}
