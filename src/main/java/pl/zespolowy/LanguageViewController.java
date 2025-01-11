package pl.zespolowy;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pl.zespolowy.Controllers.language.*;
import pl.zespolowy.Controllers.words.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class LanguageViewController {
    //language 'set' or language class?
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

    @FXML
    public void initialize(){
        System.out.println("CHECK ONE");

        //languageEnableTableColumn.setCellValueFactory(cellData -> cellData.getValue().getEnabled());
        languageTableView.setEditable(true);

        languageNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        languageEnableTableColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        languageEnableTableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(languageEnableTableColumn));
        languageEnableTableColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        String rootPath = System.getProperty("user.dir");

        String languagesPath = rootPath + "/languages.json";
        initLanguages("set1", languagesPath);
        System.out.println("CHECK TWO");
        ObservableList<Language> languages = FXCollections.observableArrayList(languageSet.getList());
        //languageSet.print();
        //System.out.println(languages.getFirst());
        languageTableView.setItems(languages);
        languages.getFirst().getEnabled();

    }


    // a function to load the data from json file (languages)
    // a function to load the data from json file (themes)
    // ... into the array column. For each Language object in the list (object - row)
    // the table will need to display a corresponding boolean checkbox and name in the table
    public void initLanguages(String title, String path) {
        try {
            String content = Files.readString(Paths.get(path));
            languageSet = new LanguageSet(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadLanguageData(){

    }
    @FXML
    public void onButtonPress(){
        //actions on Compute button press
    }
    @FXML
    public void onCheckBoxTest(){
        languageSet.print();
    }
}
