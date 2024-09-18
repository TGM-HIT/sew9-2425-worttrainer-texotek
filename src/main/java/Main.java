import model.WordImagePair;
import model.WordTrainer;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MalformedURLException {

        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("cat", new URL("https://pixnio.com/free-images/2017/09/26/2017-09-26-07-22-55.jpg")));

        WordTrainer wordTrainer = new WordTrainer(pairs);

        wordTrainer.selectNewPair();
        System.out.println(wordTrainer.getSelectedImageURL());
        System.out.println(wordTrainer.guessSelectedWord("cat"));
    }
}
