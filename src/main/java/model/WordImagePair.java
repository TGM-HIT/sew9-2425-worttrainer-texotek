package model;

import java.io.Serializable;
import java.net.URL;

public class WordImagePair implements Serializable {
    private String word;
    private URL imageURL;

    /**
     * Default Constructor
     */
    public WordImagePair() {
    }

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
     * Setter Word
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }


    /**
     * Method to get the image URL
     * @return URL of the image
     */
    public URL getImageURL() {
        return imageURL;
    }


    /**
     * Setter imageURL
     * @param imageURL
     */
    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }
}
