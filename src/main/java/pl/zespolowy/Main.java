package pl.zespolowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.zespolowy.Controllers.MainSceneController;

import java.io.IOException;
import java.util.Set;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, NoSuchFieldException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("Windows");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        launch();
        TodoClient todoClient = new TodoClient("technology");

        Set<String> wordSet = todoClient.findAll();
        for (String word : wordSet) {
            System.out.println(word);
        }

        System.out.println(wordSet.size());
    }


}