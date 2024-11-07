package pl.zespolowy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class WordSet {
    private String title;
    private List<Word> content;
    private boolean enabled;

    public WordSet(String title, String jsonString) {
        this.title = title;
        Deserialize(jsonString);
        this.enabled = false;
    }

    public WordSet(String title, String jsonString, boolean enabled) {
        this.title = title;
        Deserialize(jsonString);
        this.enabled = enabled;
    }

    private void Deserialize(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            content = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Word.class));
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

    public List<Word> getContent() {
        return content;
    }

    public void addWord(Word word) {
        content.add(word);
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}