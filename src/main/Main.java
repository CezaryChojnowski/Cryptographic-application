package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.Stack;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/resources/fxml/MainScreen.fxml"));
        StackPane stackPane = loader.load();

        Scene scene = new Scene(stackPane, 600,600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptographic application");
        primaryStage.show();
    }
}