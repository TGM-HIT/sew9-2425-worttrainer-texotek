package model;

import java.io.Serializable;
import java.net.URL;

public class WordImagePair implements Serializable {
    final private String word;
    final private URL imageURL;
    /**
     * Constructor for WordImagePair
     * @param word Word
     * @param imageURL URL of the image
     */
    public WordImagePair(String word, URL imageURL) {
        this.word = word;
        this.imageURL = imageURL;
    }

    /**
     * Method to get the word
     * @return Word
     */

    public String getWord() {
        return word;
    }

    /**
     * Method to get the image URL
     * @return URL of the image
     */
    public URL getImageURL() {
        return imageURL;
    }
}
