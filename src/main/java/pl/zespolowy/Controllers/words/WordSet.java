package pl.zespolowy.Controllers.words;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pl.zespolowy.Controllers.language.SimpleBooleanPropertyDeserializer;

import java.io.IOException;
import java.util.List;

public class WordSet {
    private String title;
    private List<Word> words;
    @JsonDeserialize(using = SimpleBooleanPropertyDeserializer.class)
    private BooleanProperty enabled;

    public WordSet(String title, String jsonString, BooleanProperty enabled) {
        this.title = title;
        deserialize(jsonString);
        this.enabled = new SimpleBooleanProperty(false);
    }

    private void deserialize(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            words = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Word.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Word> getWords() {
        return words;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public BooleanProperty getEnabled() {
        return enabled;
    }

    public void setEnabled(BooleanProperty enabled) {
        this.enabled = enabled;
    }

    // test
    public void print() {
        System.out.println("---- Words in set \"" + title + "\": ----");
        for (Word word : words) {
            System.out.println(word.getText());
        }
        System.out.println();
    }
}
