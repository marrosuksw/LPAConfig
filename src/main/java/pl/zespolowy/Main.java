package pl.zespolowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import pl.zespolowy.Controllers.MainViewController;
import pl.zespolowy.Controllers.translation.Translator;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, NoSuchFieldException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainView.fxml"));
        Parent root = fxmlLoader.load();
        MainViewController controller = fxmlLoader.getController();

        Translator translator = new Translator();
        controller.setTranslator(translator);





        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Language Proximity Analysis");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {



        //Translator translator = new Translator();
        //String result = translator.translate("dupa", "pl", "en");
        //System.out.println(result);

        launch();
        //TodoClient todoClient = new TodoClient("technology");
//
        //Set<String> wordSet = todoClient.findAll();
        //for (String word : wordSet) {
        //    System.out.println(word);
        //}
//
        //System.out.println(wordSet.size());


    }


}