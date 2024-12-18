package pl.zespolowy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import pl.zespolowy.Controllers.language.*;
import pl.zespolowy.Controllers.words.*;


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

    @FXML
    public void initialize(){}

}
