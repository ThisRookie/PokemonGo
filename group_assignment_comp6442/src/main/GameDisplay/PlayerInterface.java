package main.GameDisplay;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerInterface {

    @FXML // fx:id="output"
    public TextArea output = new TextArea("sdaf"); // Value injected by FXMLLoader
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    public void changeOutputText(String string){
        output.setText(string);
    }


//    public void changeMainText(String string){
//        textOutputArea.setText(string);
//    }
//
//    public void changeLocationText(String string){
//        locationNameLabel.setText(string);
//    }
}
