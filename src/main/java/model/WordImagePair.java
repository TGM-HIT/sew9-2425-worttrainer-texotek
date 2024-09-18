package model;

import java.net.URL;

public class WordImagePair {
    private String word;
    private URL imageURL;

    public WordImagePair(String word, URL imageURL) {
        this.word = word;
        this.imageURL = imageURL;
    }

    public String getWord() {
        return word;
    }
    public URL getImageURL() {
        return imageURL;
    }
}
