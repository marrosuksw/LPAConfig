package pl.zespolowy;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import pl.zespolowy.Controllers.words.Word;
import pl.zespolowy.Controllers.words.WordSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class WordsViewController {


    @FXML
    private VBox vBox;
    @FXML
    private TableView<WordSet> wordSetTableView;
    @FXML
    private TableColumn<WordSet, Boolean> wordSetBooleanTableColumn;
    @FXML
    private TableColumn<WordSet, String> wordSetNameTableColumn;
    @FXML
    private TableView<Word> wordTableView;
    @FXML
    private TableColumn<Word, Boolean> wordBooleanTableColumn;
    @FXML
    private TableColumn<Word, String> wordStringTableColumn;

    private WordSet wordSet;
    private Map<String, WordSet> wordSets;
    private ObservableList<WordSet> wordSetObservableList;


    @FXML
    public void initialize(){
        setValues();
    }
    private void setValues(){

        String rootPath = System.getProperty("user.dir");
        String wordSetPath = rootPath + "/wordsets/";
        wordSets = initWordSets(wordSetPath);
        wordSetObservableList = FXCollections.observableArrayList(wordSets.values());

    }

    private Map<String, WordSet> initWordSets(String path) {

        Map<String, WordSet> wordMap = new HashMap<>();

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
                        wordMap.put(title, wordSet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(fileName);
                }
                return wordMap;

            } else {
                System.out.println("The current directory is empty or an error occurred.");
            }
        } else {
            System.out.println("The current directory does not exist or is not a directory.");
        }
        return null;
    }
    
}
