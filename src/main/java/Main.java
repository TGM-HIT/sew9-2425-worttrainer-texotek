import model.WordImagePair;
import model.WordTrainer;
import persistence.WordTrainerSaver;
import persistence.WordTrainerSaverSerializable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://pixnio.com/free-images/2017/09/26/2017-09-26-07-22-55.jpg")));
        pairs.add(new WordImagePair("dog", new URL("https://img.fotocommunity.com/suesser-hund-6dc9c175-de60-44b3-a07e-9110fcee3bbf.jpg?height=1080")));

        WordTrainer wordTrainer = new WordTrainer(pairs);
        wordTrainer.selectNewPair();
        System.out.println(wordTrainer.getSelectedWord() + " " +  wordTrainer.getSelectedImageURL());

        WordTrainerSaver wordTrainerSaver = new WordTrainerSaverSerializable();

        wordTrainerSaver.save(wordTrainer, "wordTrainer.ser");
        WordTrainer loadedWordTrainer = wordTrainerSaver.load("wordTrainer.ser");

        System.out.println(loadedWordTrainer.getSelectedWord() + " " + loadedWordTrainer.getSelectedImageURL());
    }
}
