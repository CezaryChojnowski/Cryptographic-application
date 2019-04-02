package controller;

import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

import java.lang.String;

public class MatrixChange2bController {

    private MainController MainController;

    @FXML
    private TextArea Key;

    @FXML
    private TextArea PlainText;

    @FXML
    private TextArea Cryptogram;

    @FXML
    public void onPlainText() {
        int LengthPlainText = checkPlanTextLength(stringPlainTextConvertToChar(ReplaceAllSpace(getPlainText())));
        int LengthKey = checkCharKeyLength(stringKeyConvertToChar(getKey()));
        char[][] encrypt = encrypt(LengthKey, LengthPlainText);
        char[][] Sort = sortCharKey(encrypt,LengthKey,LengthPlainText);
        String encryptFinalVersion = encryptCombine(Sort,LengthKey, LengthPlainText);
        Cryptogram.setText(encryptFinalVersion);
    }

    @FXML
    public void onCryptogram() {
        int charCryptogramLength = checkPlanTextLength(stringPlainTextConvertToChar(getCryptogram()));
        int LengthKey = checkCharKeyLength(stringKeyConvertToChar(getKey()));
        String decryptionFinalVersion = decryption(LengthKey,charCryptogramLength);
        PlainText.setText(decryptionFinalVersion);
    }

    @FXML
    public void onBack() {
        MainController.loadMenuScreen();
    }

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

    public String ReplaceAllSpace(String CryptogramWithSpace) {
        return CryptogramWithSpace.replace(" ", "");
    }

    public char[] stringPlainTextConvertToChar(String stringPlainText) {
        return stringPlainText.toCharArray();
    }

    public void setMainController(MainController mainController) {
        MainController = mainController;
    }

    public char[] stringKeyConvertToChar(String stringKey) {
        return stringKey.toCharArray();
    }

    public int checkCharKeyLength(char[] key) {
        return key.length;
    }

    public int checkPlanTextLength(char[] plainText) {
        return plainText.length;
    }

    public char[][] sortCharKey(char[][] unsortedArray, int charKeyLength, int charPlainTextLength) {
        int numberOfRows = ((int)(Math.ceil(((double)(charPlainTextLength))/((double)(charKeyLength)))+2.0));
        char[] ArrayTemp = new char[charKeyLength];
        for (int i = 1; i < charKeyLength; i++) {
            for (int j = 0; j < charKeyLength-1; j++) {
                if (unsortedArray[1][j] > unsortedArray[1][j+1]) {
                    for(int k=0; k<numberOfRows; k++){
                            ArrayTemp[k] = unsortedArray[k][j];
                            unsortedArray[k][j]=unsortedArray[k][j+1];
                            unsortedArray[k][j+1]=ArrayTemp[k];
                    }
                }
            }
        }
        return unsortedArray;
    }

    public char[][] encrypt(int charKeyLength, int charPlainTextLength) {
        int numberOfRows = ((int)(Math.ceil(((double)(charPlainTextLength))/((double)(charKeyLength)))+2.0));
        int currentLetter = 0;
        char[][] Array2 = new char[numberOfRows][charKeyLength];
        for (int j = 0; j < charKeyLength; j++) {
            Array2[0][j] = (char) j;
        }
        for (int j = 0; j < charKeyLength; j++) {
            Array2[1][j] = stringKeyConvertToChar(getKey())[j];
        }
        for (int i = 2; i < numberOfRows; i++) {
            for (int j = 0; j < charKeyLength; j++, currentLetter++) {
                if ((i == numberOfRows-1) && j==((checkPlanTextLength(stringPlainTextConvertToChar(ReplaceAllSpace(getPlainText()))))%(charKeyLength))) {
                    break;
                }
                else{
                    Array2[i][j] = (stringPlainTextConvertToChar(ReplaceAllSpace(getPlainText())))[currentLetter];
                }
            }
        }
        int lastRow=numberOfRows-1;
        int theLastNonEmptyIndexInTheLastRow=((checkPlanTextLength(stringPlainTextConvertToChar(ReplaceAllSpace(getPlainText()))))%(charKeyLength));

           for (int j = theLastNonEmptyIndexInTheLastRow; j < charKeyLength; j++) {
                if ((j>=theLastNonEmptyIndexInTheLastRow)) {
                    Array2[lastRow][j]='0';
                }
                else{
                    continue;
                }
            }
        return Array2;
    }

    public String encryptCombine(char[][] sortArray, int charKeyLength, int charPlainTextLength ){

        int numberOfRows = ((int)(Math.ceil(((double)(charPlainTextLength))/((double)(charKeyLength)))+2.0));

        String encryptedMessage = new String();
        for(int i=0; i<charKeyLength; i++){
            for(int j=2; j<numberOfRows; j++){
                encryptedMessage = encryptedMessage + Character.toString(sortArray[j][i]);
            }
        }
        encryptedMessage = encryptedMessage.replace("0", "");
        return encryptedMessage;
    }

    public String decryption(int charKeyLength, int charCryptogramLength){
        int fullColumns = ((int)(Math.ceil(((double)(charCryptogramLength))/((double)(charKeyLength)))+2.0));
        char[][] Array2 = new char[fullColumns][charKeyLength];
        int n = charCryptogramLength%charKeyLength;
        char[] arrayTemp = new char[charKeyLength-(n)];
        int incompleteColumn=0;
        int indexIncompleteColumn=0;
        int currentLetter=0;
        int P=0;
        String decryptedMessage = new String();
        for (int j = 0; j < charKeyLength; j++) {
            Array2[0][j] = (char) j;
        }
        for (int j = 0; j < charKeyLength; j++) {
            Array2[1][j] = stringKeyConvertToChar(getKey())[j];
        }
        for(int k=0; k<charKeyLength-(n); k++){
            arrayTemp[k]=Array2[0][n+k];
        }
        char[] temp = new char[charKeyLength];
        for(int i=0; i<charKeyLength; i++){
            for(int j=0; j<charKeyLength-1; j++){
                if(Array2[1][j]>Array2[1][j+1]){
                    for(int k=0; k<2; k++){
                        temp[k] = Array2[k][j];
                        Array2[k][j]=Array2[k][j+1];
                        Array2[k][j+1]=temp[k];
                    }
                }
            }
        }
        while(incompleteColumn<arrayTemp.length){
            if(Array2[0][indexIncompleteColumn]==arrayTemp[incompleteColumn]){
                arrayTemp[incompleteColumn]=(char)indexIncompleteColumn;
                incompleteColumn++;
                indexIncompleteColumn=0;
            }
            else{
                indexIncompleteColumn=indexIncompleteColumn+1;
            }
        }
        for (int i = 0; i < charKeyLength; i++) {
            for (int j = 2; j < fullColumns; j++) {
                P=0;
                for(int k=0; k<charKeyLength-(n); k++){
                    if(j==fullColumns-1 && (i==arrayTemp[k])){
                        Array2[j][i]='0';
                        P=1;
                        break;
                    }
                }
                if(P==0){
                    Array2[j][i] = (stringPlainTextConvertToChar(getCryptogram()))[currentLetter];
                    currentLetter++;
                }
            }

        }
        for(int i=0; i<charKeyLength; i++){
            for(int j=0; j<charKeyLength-1; j++){
                if(Array2[0][j]>Array2[0][j+1]){
                    for(int k=0; k<fullColumns; k++){
                        temp[k] = Array2[k][j];
                        Array2[k][j]=Array2[k][j+1];
                        Array2[k][j+1]=temp[k];
                    }
                }
            }
        }
        for(int i=2; i<fullColumns; i++){
            for(int j=0; j<charKeyLength; j++){
                String s = Character.toString(Array2[i][j]);
                decryptedMessage = decryptedMessage + s;
            }
        }
        decryptedMessage = decryptedMessage.replace("0", "");
        return  decryptedMessage;
            }
        }