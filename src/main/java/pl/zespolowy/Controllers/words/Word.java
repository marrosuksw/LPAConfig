package pl.zespolowy.Controllers.words;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pl.zespolowy.Controllers.language.SimpleBooleanPropertyDeserializer;

public class Word {
    private String text;
    @JsonDeserialize(using = SimpleBooleanPropertyDeserializer.class)
    private BooleanProperty enabled;

    public Word() {}

    public Word(String text) {
        this.text = text;
        this.enabled = new SimpleBooleanProperty(false);
    }

    public Word(String text, BooleanProperty enabled) {
        this.text = text;
        this.enabled = new SimpleBooleanProperty(false);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BooleanProperty getEnabled() {
        return enabled;
    }

    public void setEnabled(BooleanProperty enabled) {
        this.enabled = enabled;
    }
}
