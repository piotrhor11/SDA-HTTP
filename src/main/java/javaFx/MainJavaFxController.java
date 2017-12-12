package javaFx;

import commons.CryptoCurrencyDTO;
import http.Exercise3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

public class MainJavaFxController implements Initializable {

    @FXML
    private Label result;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField amount;

    @FXML
    private void convert(ActionEvent event){

        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal number = new BigDecimal(amount.getText());
        String choice = choiceBox.getValue();

        HashSet<CryptoCurrencyDTO> ccDTOHashSet = Exercise3.getCryptoCurrencyDTOHashSet();

        for (CryptoCurrencyDTO ccDTO: ccDTOHashSet) {
            if (choice.equals(ccDTO.getName())){
                rate = ccDTO.getPrice_usd();
            }
        }
        result.setText((number.multiply(rate)).toString());
    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public void setChoiceBox(ChoiceBox<String> choiceBox) {
        this.choiceBox = choiceBox;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
