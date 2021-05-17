package main.GameDisplay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * This class is the controller to control the Game playing stage
 */
public class GameMainSceneController {
    @FXML
    private TextArea Text_field;

    @FXML
    private Button implementButton;

    @FXML
    private Label Name_Lable;

    @FXML
    private TextArea Game_Info;

    String textArea;

    @FXML
    private Button launchgame;

    @FXML
    void Launch_game(ActionEvent event) {


    }

    /**
     * This function could print the text in the Game_Info text area.
     * @param event
     */
    @FXML
    public void implementGame(ActionEvent event) {
//        try {
//            textArea = Text_field.getText();
//        }
//        catch (Exception e){
//            System.out.println();
//        }
        textArea = Text_field.getText();
        Game_Info.setText(textArea);
    }
    public void outputContext(String str){
        Game_Info.setText(str);
    }

    /**
     * This is the function to launch the Gamemain stage, but no longer used
     * @param primaryStage
     * @throws Exception
     */
    public void launch(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(GameShow.class.getResource("GameMainScene.fxml"));
        primaryStage.setTitle("PokemonGo");
        primaryStage.setScene(new Scene(root,800,700));
        primaryStage.show();
    }

    /**
     * This function is to initialize the Pokemon_Hallowwen image
     * @return
     */
    public ImageView getImage() {
        Image image = new Image("/GameDisplay/Pokemon_Halloween.png");
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
        return imageView;
    }

}
