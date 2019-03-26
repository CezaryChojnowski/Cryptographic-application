package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;


public class CaesarCipherController {

    private MainController MainController;

    private static int firstLetterInASCII = 65;
    private static int lastLetterInASCII = 90;

    @FXML
    private TextArea Key;

    @FXML
    private TextArea PlainText;

    @FXML
    private TextArea Cryptogram;

    @FXML
    public String getKey() {
        return Key.getText();
    }

    @FXML
    public String getPlainText() {
        return PlainText.getText();
    }

    @FXML
    public String getCryptogram() {
        return Cryptogram.getText();
    }

    @FXML
    public void onPlainText(){
        String Upper = convertStringToUpperCase(getPlainText());
        Cryptogram.setText(encryptPlainText(convertStringToChar(Upper)));
    }

    @FXML
    public void onCryptogram(){
        String Upper = convertStringToUpperCase(getCryptogram());
        PlainText.setText(descriptionCryptogram(convertStringToChar(Upper)));
    }

    @FXML
    public void onBack(){
        MainController.loadMenuScreen();
    }

    public char[] convertStringToChar(String stringWord){
        return stringWord.toCharArray();
    }

    public int convertStringToInt(String stringNumber){
        return Integer.valueOf(stringNumber);
    }

    public String convertStringToUpperCase(String word){
        return word.toUpperCase();
    }

    public int checkLengthWord(char[] word){
        return word.length;
    }

    public String encryptPlainText(char[] plainText) {
        int numberOfLitersToTheEndOfTheAlphabet;
        int numberOfPassesFromTheBeginningOfTheAlphabet;
        int currentLetter;
        int key=0;
        int lengthPlainText = checkLengthWord(plainText);
        String encryptedMessage = new String();
        try{
            key = convertStringToInt(getKey());
        }catch (Exception ex){
            Alert dg = new Alert(Alert.AlertType.INFORMATION);
            dg.setTitle("Error");
            dg.setHeaderText("Enter the value of the key\n");
            dg.show();
        }

        for (int i = 0; i < lengthPlainText; i++) {
            currentLetter = (int) plainText[i];
            if (currentLetter + key <= lastLetterInASCII) {
                plainText[i] = (char) ((currentLetter) + key);
            } else {
                numberOfLitersToTheEndOfTheAlphabet = lastLetterInASCII - currentLetter;
                numberOfPassesFromTheBeginningOfTheAlphabet = key - numberOfLitersToTheEndOfTheAlphabet - 1;
                plainText[i] = (char) (firstLetterInASCII + numberOfPassesFromTheBeginningOfTheAlphabet);
            }
        }
        for (int i = 0; i < lengthPlainText; i++) {
            encryptedMessage = encryptedMessage + Character.toString(plainText[i]);
        }
        return encryptedMessage;
    }

    public String descriptionCryptogram(char[] cryptogram){
        int lengthCryptogram = checkLengthWord(cryptogram);
        int key=0;
        int currentLetter;
        int numberOfLitersToTheBeginningOfTheAlphabet;
        int numberOfPassesFromTheEndOfTheAlphabet;
        String decryptedMessage = new String();
        try{
            key = convertStringToInt(getKey());
        }catch (Exception ex){
            Alert dg = new Alert(Alert.AlertType.INFORMATION);
            dg.setTitle("Error");
            dg.setHeaderText("Enter the value of the key\n");
            dg.show();
        }

        for(int i=0; i<lengthCryptogram; i++){
            currentLetter = cryptogram[i];
            if(currentLetter - key >= firstLetterInASCII){
                cryptogram[i] = (char)((currentLetter-key));
            }
            else{
                numberOfLitersToTheBeginningOfTheAlphabet = currentLetter - firstLetterInASCII;
                numberOfPassesFromTheEndOfTheAlphabet = key - numberOfLitersToTheBeginningOfTheAlphabet-1;
                cryptogram[i]=(char)(lastLetterInASCII-numberOfPassesFromTheEndOfTheAlphabet);

            }
        }
        for(int i=0; i<lengthCryptogram; i++){
            decryptedMessage = decryptedMessage + Character.toString(cryptogram[i]);
        }
            return decryptedMessage;
    }

    public void setMainController(controller.MainController mainController) {
        MainController = mainController;
    }
}
