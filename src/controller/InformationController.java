package controller;

import javafx.fxml.FXML;

public class InformationController {

    private MainController MainController;

    @FXML
    public void onBack(){
        MainController.loadMenuScreen();
    }

    public void setMainController(controller.MainController mainController) {
        MainController = mainController;
    }
}
