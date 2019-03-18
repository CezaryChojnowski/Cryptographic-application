package controller;

import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import java.lang.String;
import java.util.Arrays;


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
        Cryptogram.setText(ReplaceAllSpace(getPlainText()));
        enterPlainTextIntoTheArray(createArray(rowsArray(), columnsArray()));
    }

    @FXML
    public void onCryptogram() {
        Cryptogram.setText(sortKey(getKey()));
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

    public String sortKey(String unsortedKey) {
        char tempArray[] = unsortedKey.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public int checkLengthPlainTextWithoutSpace(String plainTextWithoutSpace) {
        return plainTextWithoutSpace.length();
    }

    public int checkLengthKey(String key) {
        return key.length();
    }

    public int rowsArray() {
        return new Integer((int) (Math.ceil(((double) (checkLengthPlainTextWithoutSpace(ReplaceAllSpace(getPlainText()))) / (double) (checkLengthKey(getKey()))))));
    }

    public int columnsArray() {
        return new Integer(checkLengthKey(getKey()));
    }

    public String[][] createArray(int x, int y) {
        return new String[rowsArray()][columnsArray()];
    }

    public void setMainController(MainController mainController) {
        MainController = mainController;
    }

    public void enterPlainTextIntoTheArray(String array[][]) {
        int x = 0;
        for (int i = 0; i < rowsArray(); i++) {
            for (int j = 0; j < columnsArray(); j++, x++) {
                //IF tylko dla przypadku PlainText=24, Key=7
                if (i == 3 && j == 3) {
                    break;
                } else {
                    array[i][j] = String.valueOf(((ReplaceAllSpace(getPlainText())).charAt(x)));
                    System.out.println(array[i][j]);
                }
            }

        }
    }
}
