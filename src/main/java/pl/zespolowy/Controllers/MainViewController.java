package pl.zespolowy.Controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pl.zespolowy.Controllers.language.LanguageSet;
import pl.zespolowy.Controllers.translation.Translation;
import pl.zespolowy.Controllers.translation.Translator;
import pl.zespolowy.Controllers.words.WordSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MainViewController {

    private Translator translator;
    private Map<String, WordSet> wordSets;
    private LanguageSet languageSet;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab themesLanguagesTab;
    @FXML
    private Tab themesWordsTab;

    @FXML
    public void initialize() {
        //loadTabOneContent();
    }

    private void loadTabOneContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LanguageView.fxml"));
            VBox tabOneContent = loader.load();
            themesLanguagesTab.setContent(tabOneContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initLanguages(String title, String path) {
        try {
            String content = Files.readString(Paths.get(path));
            languageSet = new LanguageSet(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public void initWordSets(String path) {

        wordSets = new HashMap<>();

        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            String[] fileNames = dir.list();

            if (fileNames != null) {
                for (String fileName : fileNames) {
                    try {
                        String title = fileName.split(".json")[0];
                        String content = Files.readString(Paths.get(path + fileName));

                        SimpleBooleanProperty newBool = new SimpleBooleanProperty(false);
                        WordSet wordSet = new WordSet(title, content, newBool);
                        wordSets.put(title, wordSet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(fileName);
                }

            } else {
                System.out.println("The current directory is empty or an error occurred.");
            }
        } else {
            System.out.println("The current directory does not exist or is not a directory.");
        }
    }

}
