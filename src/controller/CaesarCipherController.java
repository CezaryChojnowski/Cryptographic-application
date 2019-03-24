package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class CaesarCipherController {

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
        int numberOfLitersToTheEndOfTheAlphabet, numberOfPassesFromTheBeginningOfTheAlphabet, ASCIIletter, key = convertStringToInt(getKey()), lengthPlainText = checkLengthWord(plainText);
        String encryptedMessage = new String();
        for (int i = 0; i < lengthPlainText; i++) {
            ASCIIletter = (int) plainText[i];
            if (ASCIIletter + key <= lastLetterInASCII) {
                plainText[i] = (char) ((ASCIIletter) + key);
            } else {
                numberOfLitersToTheEndOfTheAlphabet = lastLetterInASCII - ASCIIletter;
                numberOfPassesFromTheBeginningOfTheAlphabet = key - numberOfLitersToTheEndOfTheAlphabet - 1;
                plainText[i] = (char) (firstLetterInASCII + numberOfPassesFromTheBeginningOfTheAlphabet);
            }
            System.out.print(plainText[i]);
        }
        for (int i = 0; i < lengthPlainText; i++) {
            encryptedMessage = encryptedMessage + Character.toString(plainText[i]);
        }
        return encryptedMessage;
    }

    public String descriptionCryptogram(char[] cryptogram){
        int lengthCryptogram = checkLengthWord(cryptogram), key=convertStringToInt(getKey()), ASCIIletter, numberOfLitersToTheBeginningOfTheAlphabet, numberOfPassesFromTheEndOfTheAlphabet;
        String decryptedMessage = new String();
        for(int i=0; i<lengthCryptogram; i++){
            ASCIIletter = cryptogram[i];
            if(ASCIIletter - key >= firstLetterInASCII){
                cryptogram[i] = (char)((ASCIIletter-key));
            }
            else{
                numberOfLitersToTheBeginningOfTheAlphabet = ASCIIletter - firstLetterInASCII;
                numberOfPassesFromTheEndOfTheAlphabet = key - numberOfLitersToTheBeginningOfTheAlphabet-1;
                cryptogram[i]=(char)(lastLetterInASCII-numberOfPassesFromTheEndOfTheAlphabet);

            }
            //System.out.print(cryptogram[i]);
        }
        for(int i=0; i<lengthCryptogram; i++){
            decryptedMessage = decryptedMessage + Character.toString(cryptogram[i]);
        }
            return decryptedMessage;
    }

    private MainController MainController;

    public void setMainController(controller.MainController mainController) {
        MainController = mainController;
    }
}
