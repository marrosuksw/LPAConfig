package pl.zespolowy.Controllers.language;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Language {
    private String name;
    private String code;
    @JsonDeserialize(using = SimpleBooleanPropertyDeserializer.class)
    private BooleanProperty enabled;

    public Language() {
    }

    public Language(String name, String code) {
        this.name = name;
        this.code = code;
        this.enabled = new SimpleBooleanProperty(false);
    }

    public Language(String name, String code, BooleanProperty enabled) {
        this.name = name;
        this.code = code;
        this.enabled = new SimpleBooleanProperty(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BooleanProperty getEnabled() {
        return enabled;
    }

    public void setEnabled(BooleanProperty enabled) {
        this.enabled = enabled;
    }
}
