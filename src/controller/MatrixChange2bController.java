package controller;

import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import java.lang.String;


public class MatrixChange2bController{

    private MainController MainController;

    @FXML
    private TextArea Key;

    @FXML
    private TextArea PlainText;

    @FXML
    private TextArea Cryptogram;

    @FXML
    public void onPlainText(){
        Cryptogram.setText(ReplaceAllSpace(getPlainText()));
    }

    @FXML
    public void onCryptogram(){
    }

    @FXML
    public void onBack(){
        MainController.loadMenuScreen();
    }

    @FXML
    public String getKey(){
        return Key.getText();
    }

    @FXML
    public String getPlainText(){
        return PlainText.getText();
    }

    @FXML
    public String getCryptogram(){
        return Cryptogram.getText();
    }

    public String ReplaceAllSpace(String CryptogramWithSpace){
        return CryptogramWithSpace.replace(" ","");
    }

    public void setMainController(MainController mainController) {
        MainController = mainController;
    }
}
