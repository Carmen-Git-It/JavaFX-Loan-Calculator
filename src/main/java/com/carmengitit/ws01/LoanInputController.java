package com.carmengitit.ws01;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;

import static java.lang.Math.pow;

public class LoanInputController {
    @FXML
    private TextField typeText;
    @FXML
    private TextField ageText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField downPaymentText;
    @FXML
    private TextField interestRateText;
    @FXML
    private Slider paymentPeriodSlider;
    @FXML
    private Label loanPeriodLabel;
    @FXML
    private TextField paymentFrequencyText;
    @FXML
    private TextField calculateText;

    @FXML
    protected void onCalculateButtonClick() {
        double payment = 0;
        int numPayments = 0;

        try {
            double price = Double.parseDouble(priceText.getText());
            double down = Double.parseDouble(downPaymentText.getText());
            double interest = Double.parseDouble(interestRateText.getText()) / 100;    // Convert to decimal
            int months = (int) paymentPeriodSlider.getValue();
            String frequency = paymentFrequencyText.getText();

            if (frequency.equalsIgnoreCase("weekly")) {
                numPayments = months * 4;
                interest /= (12 * 4);
            } else if (frequency.equalsIgnoreCase("bi-weekly") || frequency.equalsIgnoreCase("biweekly")) {
                numPayments = months * 2;
                interest /= (12 * 2);
            } else if (frequency.equalsIgnoreCase("monthly")) {
                numPayments = months;
                interest /= (12);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Invalid Payment Frequency");
                alert.setContentText("Please enter a valid payment frequency: weekly, biweekly, monthly.");
                alert.showAndWait();
            }

            price -= down;
            payment = (interest * price) / (1 - (pow(1 + interest, (-1) * numPayments)));

            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String strPayment = formatter.format(payment);
            calculateText.setText(strPayment);
        } catch(Exception e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Invalid Inputs");
            alert.setContentText("Please enter your information correctly and try again.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onClearButtonClick() {
        typeText.setText("");
        ageText.setText("");
        priceText.setText("");
        downPaymentText.setText("");
        interestRateText.setText("");
        paymentPeriodSlider.setValue(12);
        paymentFrequencyText.setText("");
        calculateText.setText("");
        loanPeriodLabel.setText("Loan Payment Period:");
    }

    public void initialize() {
        paymentPeriodSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                loanPeriodLabel.setText("Loan Payment Period: " + t1.intValue() + " months");
            }
        });
    }
}