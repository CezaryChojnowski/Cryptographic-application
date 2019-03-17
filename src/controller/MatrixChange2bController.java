package controller;

import javafx.fxml.FXML;

public class MatrixChange2bController {

    private MainController MainController;

    @FXML
    public void onEncrypt(){

    }

    @FXML
    public void onDecrypt(){

    }

    @FXML
    public void onBack(){
        MainController.loadMenuScreen();
    }

    public void setMainController(MainController mainController) {
        MainController = mainController;
    }


}
