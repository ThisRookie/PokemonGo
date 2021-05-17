package main.GameDisplay;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameShow extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(GameShow.class.getResource("PlayerInterface.fxml"));
        primaryStage.setTitle("PokemonGo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
