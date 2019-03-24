package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private MainController MainController;

    @FXML
    public void onInformation(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/InformationScreen.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        InformationController InformationController = loader.getController();
        InformationController.setMainController(MainController);
        MainController.setScreen(pane);
    }


    @FXML
    public void onRailFence(){

    }

    @FXML
    public void onMatrixChange2a(){

    }

    @FXML
    public void onMatrixChange2b(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/MatrixChange2bScreen.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        MatrixChange2bController MatrixChange2bController = loader.getController();
        MatrixChange2bController.setMainController(MainController);
        MainController.setScreen(pane);
    }

    @FXML
    public void onCeasarCipher(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/CaesarCipher.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        CaesarCipherController CaesarCipherController = loader.getController();
        CaesarCipherController.setMainController(MainController);
        MainController.setScreen(pane);
    }

    public void setMainController(MainController mainController) {
        this.MainController = mainController;
    }

}
