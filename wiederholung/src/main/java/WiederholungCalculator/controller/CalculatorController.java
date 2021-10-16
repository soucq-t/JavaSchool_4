package WiederholungCalculator.controller;

import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    @FXML
    private ChoiceBox<Integer> choiceBox;

    @FXML
    private Slider slider;

    @FXML
    private RadioButton rbAdd;

    @FXML
    private RadioButton rbSub;

    @FXML
    private RadioButton rbMul;

    @FXML
    private RadioButton rbDiv;

    @FXML
    private Text textErgebnis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rbAdd.setSelected(true);
        rbAdd.setToggleGroup(toggleGroup);
        rbSub.setToggleGroup(toggleGroup);
        rbMul.setToggleGroup(toggleGroup);
        rbDiv.setToggleGroup(toggleGroup);
        List<Integer> list = new ArrayList<Integer>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        choiceBox.setItems(FXCollections.observableArrayList(list));
        choiceBox.getSelectionModel().selectFirst();

        textErgebnis.textProperty().bind(new StringBinding() {
            {
                super.bind(toggleGroup.selectedToggleProperty(), choiceBox.valueProperty(), slider.valueProperty());
            }

            @Override
            protected String computeValue() {
                switch (((RadioButton) toggleGroup.getSelectedToggle()).getText()) {
                    case "+":
                        return "" + (int) slider.getValue() + ((RadioButton) toggleGroup.getSelectedToggle()).getText() + choiceBox.getValue()
                                + "=" + ((int) slider.getValue() + choiceBox.getValue());
                    case "-":
                        return "" + (int) slider.getValue() + ((RadioButton) toggleGroup.getSelectedToggle()).getText() + choiceBox.getValue()
                                + "=" + ((int) slider.getValue() - choiceBox.getValue());
                    case "*":
                        return "" + (int) slider.getValue() + ((RadioButton) toggleGroup.getSelectedToggle()).getText() + choiceBox.getValue()
                                + "=" + ((int) slider.getValue() * choiceBox.getValue());
                    case "/":
                        if (choiceBox.getValue() != 0) {
                            return "" + (int) slider.getValue() + ((RadioButton) toggleGroup.getSelectedToggle()).getText() + choiceBox.getValue()
                                    + "=" + ((int) slider.getValue() / choiceBox.getValue());
                        }
                        return "Cannot divide by 0";

                    default:
                        return "Irgendetwas ist falsch";
                }
            }
        });


    }
}
