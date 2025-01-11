package pl.zespolowy.Controllers.language;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Language {
    private String name;
    private String code;
    private Boolean enabled;

    public Language() {
    }

    public Language(String name, String code) {
        this.name = name;
        this.code = code;
        this.enabled = false;
    }

    public Language(String name, String code, BooleanProperty enabled) {
        this.name = name;
        this.code = code;
        this.enabled = false;
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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
