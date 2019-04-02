package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class RailFanceController {

    private MainController MainController;

    @FXML
    private TextArea Rails;

    @FXML
    private TextArea PlainText;

    @FXML
    private TextArea Cryptogram;

    @FXML
    public void onPlainText() {
        Cryptogram.setText(this.encryption(getPlainText(), Integer.parseInt(this.getRails())));
        PlainText.clear();
    }

    @FXML
    public void onCryptogram() {
        PlainText.setText(this.decryption(getCryptogram(), Integer.parseInt(this.getRails())));
        Cryptogram.clear();
    }

    @FXML
    public void onBack() {
        MainController.loadMenuScreen();
    }

    @FXML
    public String getRails() {
        return Rails.getText();
    }

    @FXML
    public String getPlainText() {
        return PlainText.getText();
    }

    @FXML
    public String getCryptogram() {
        return Cryptogram.getText();
    }

    public void setMainController(MainController mainController) {

        MainController = mainController;
    }

    public String encryption(String str,int rails){

        boolean checkdown = false;
        int j = 0;
        int row = rails;
        int col = str.length();
        char[][] a = new char[row][col];

        for(int i=0; i<col; i++){
            if(j == 0||j == row-1) {
                checkdown=!checkdown;
            }
            a[j][i] = str.charAt(i);
            if(checkdown){
                j++;
            } else {
                j--;
            }
        }

        String en="";

        for(int i=0; i<row; i++){
            for(int k=0; k<col; k++){
                if(a[i][k]!=0) {
                    en = en + a[i][k];
                }
            }
        }

        return en;
    }

    public String decryption(String str, int rails){

        boolean checkdown=false;
        int j = 0;
        int row = rails;
        int col = str.length();
        char[][] a = new char[row][col];


        for(int i=0; i<col; i++){
            if(j == 0||j == row-1) {
                checkdown =!checkdown;
            }

            a[j][i]='*';
            if(checkdown) {
                j++;
            } else {
                j--;
            }

        }

        int index=0;

        for(int i=0; i<row; i++){
            for(int k=0; k<col; k++){
                if(a[i][k]=='*'&&index<str.length()){
                    a[i][k]=str.charAt(index++);
                }
            }
        }

        checkdown=false;
        String s="";
        j=0;

        for(int i=0;i<col;i++){
            if( j==0||j==row-1) {
                checkdown=!checkdown;
            }

            s+=a[j][i];

            if(checkdown){
                j++;
            } else {
                j--;
            }
        }
        return s;
    }
}
