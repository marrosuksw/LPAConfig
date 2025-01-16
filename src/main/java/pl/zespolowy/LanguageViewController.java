package pl.zespolowy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.checkerframework.checker.units.qual.C;
import pl.zespolowy.Controllers.language.*;
import pl.zespolowy.Controllers.words.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class LanguageViewController {
    //language 'set' or language class?
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Language> languageTableView;
    @FXML
    private TableView<WordSet> themeTableView;
    @FXML
    private Button button;
    @FXML
    private TableColumn<WordSet, String> themeNameTableColumn;
    @FXML
    private TableColumn<WordSet, Boolean> themeEnableTableColumn;
    @FXML
    private TableColumn<Language, String> languageNameTableColumn;
    @FXML
    private TableColumn<Language, Boolean> languageEnableTableColumn;

    private LanguageSet languageSet;

    private ObservableList<Language> languages;

    private Map<String, WordSet> wordSets;

    private ObservableList<WordSet> themes;

    @FXML
    public void initialize(){
        VBox.setVgrow(hBox, Priority.ALWAYS);
        HBox.setHgrow(themeTableView, Priority.ALWAYS);
        setThemeValues();
        setLanguageValues();
    }
    private void setThemeValues(){
        String rootPath = System.getProperty("user.dir");
//        //theme table
        themeTableView.setEditable(true);

        themeNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        themeEnableTableColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        themeEnableTableColumn.setCellFactory(tc-> new CheckBoxTableCell<>());
        themeEnableTableColumn.setCellValueFactory(cellData -> cellData.getValue().getEnabled());

        String wordSetPath = rootPath + "/wordsets/";
        wordSets = initWordSets(wordSetPath);
        themes = FXCollections.observableArrayList(wordSets.values());

        themeTableView.setItems(themes);
    }
    private void setLanguageValues(){
        String rootPath = System.getProperty("user.dir");
        languageTableView.setEditable(true);

        languageNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        languageEnableTableColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        //languageEnableTableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(languageEnableTableColumn.));
        languageEnableTableColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        languageEnableTableColumn.setCellValueFactory(cellData -> cellData.getValue().getEnabled());


        String languagesPath = rootPath + "/languages.json";
        languageSet = initLanguages("set1", languagesPath);
        languageSet.print();
        if (languageSet == null || languageSet.getList() == null) {
            throw new IllegalStateException("LanguageSet or its list is not initialized!");
        }
        languages = FXCollections.observableArrayList(languageSet.getList());

        //System.out.println(languages.getFirst());
        languageTableView.setItems(languages);
    }

    // a function to load the data from json file (languages)
    // a function to load the data from json file (themes)
    // ... into the array column. For each Language object in the list (object - row)
    // the table will need to display a corresponding boolean checkbox and name in the table
    private LanguageSet initLanguages(String title, String path) {
        try {
            String content = Files.readString(Paths.get(path));
            if (Files.notExists(Paths.get(path))) {
                System.err.println("JSON file not found: " + path);
                return null;
            }
            if (content.isEmpty()) {
                System.err.println("JSON file is empty: " + path);
                return null;
            }
            return languageSet = new LanguageSet(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @FXML
    public void onButtonPress(){
        //prototype for testing
        languages.forEach(language -> System.out.println(language.getEnabled()));
        System.out.println("and for words:");
        themes.forEach(wordSet -> System.out.println(wordSet.getEnabled()) );
        //actions on Compute button press
    }
    private Map initWordSets(String path) {

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
                return wordSets;

            } else {
                System.out.println("The current directory is empty or an error occurred.");
            }
        } else {
            System.out.println("The current directory does not exist or is not a directory.");
        }
        return null;
    }
}
