package pl.zespolowy.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import pl.zespolowy.Translation;
import pl.zespolowy.Translator;
import pl.zespolowy.Word;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;



@Getter
public class MainSceneController {
    @FXML
    private AnchorPane mainSceneAnchorPane;

    @FXML
    private TextArea textArea;

    private Translator translator;
    private Map<String, List<Word>> wordSets;

    public void initialize() {}

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public void initWordSets(String path) {
        {
            String fileName = "example_dupa123.txt";
            File file = new File(fileName);

            try {
                // Check if the file already exists
                if (!file.exists()) {
                    // Create the file
                    file.createNewFile();
                    System.out.println("File created: " + file.getName());
                }

                // Use FileWriter to write to the file
                FileWriter writer = new FileWriter(file);
                writer.write("Hello, world!\nThis is a test file.");
                writer.close(); // Always close the writer
                System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        File dir = new File(path);
        String[] files = dir.list();
        System.out.println("Files:\n");
        if (files != null) {
            for (String fileName : files) {
                System.out.println(fileName);
            }
        }
    }

    @FXML
    public void handleTranslate() {
        String wordSet = "jabłko; banan; cytryna";

        Translation translation = translator.translate(wordSet, "pl", "en");

        System.out.println("----- LIST -------- ");
        for (String str : translation.toList()) {
            System.out.println("'" + str + "'");
        }
        System.out.println();

        textArea.appendText(translation.multiLine());
    }
}
