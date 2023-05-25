package com.carmengitit.ws01;

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
    private TextField paymentFrequencyText;
    @FXML
    private TextField calculateText;

    @FXML
    protected void onCalculateButtonClick() {
        double payment = 0;
        double price = 0;
        double down = 0;
        double interest = 0;
        int months = 0;
        int numPayments = 0;
        String frequency = "";

        try {
            price = Double.parseDouble(priceText.getText());
            down = Double.parseDouble(downPaymentText.getText());
            interest = Double.parseDouble(interestRateText.getText()) / 100;    // Convert to decimal
            months = (int) paymentPeriodSlider.getValue();
            frequency = paymentFrequencyText.getText();

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
    }
}