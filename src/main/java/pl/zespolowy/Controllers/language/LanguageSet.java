package pl.zespolowy.Controllers.language;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import org.apache.commons.codec.language.bm.Languages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanguageSet {
    private String title;
    private List<Language> languages;

    public LanguageSet(String title, String jsonString) {
        this.title = title;
        Deserialize(jsonString);
    }

    private void Deserialize(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            languages = new ArrayList<>();
            languages = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Language.class));
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

    public List<Language> getList() {
        return languages;
    }

    public void addToList(Language language) {
        languages.add(language);
    }

    // test
    public void print() {
        System.out.println("---- Languages in set \"" + title + "\": ----");
        if (languages.isEmpty()) System.out.println("Empty list!!!");
        for (Language l : languages) {
            System.out.println(l.getName() + " ");
            System.out.println(l.getEnabled());
        }
        System.out.println();
    }
}
