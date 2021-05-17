package main.GameDisplay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {


    @FXML
    private TextField txt;
    @FXML
    private ImageView imageView;

    @FXML
    void show_message(ActionEvent event) {
        String message = txt.getText();
        System.out.println(txt.getText());
    }

    @FXML
    private TextField passcode;

    private String username;
    private int Passcode;

    public ImageView getImage() {
        Image image = new Image("/GameDisplay/pokemongo.png");
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
        return imageView;

    }


    public void sendToGameMainScence(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameMainScene.fxml"));
        Parent root = loader.load();
        GameMainSceneController gameMainSceneController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}