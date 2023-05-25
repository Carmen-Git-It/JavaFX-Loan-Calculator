/**********************************************
 Workshop 1
 Course: Application Development (APD545) - Semester 5
 Last Name: Whitton
 First Name: Carmen
 ID: 102710217
 Section: NAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature: Carmen Whitton
 Date: 2023/05/28
 **********************************************/
package com.carmengitit.ws01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CarLoanApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarLoanApplication.class.getResource("car-loan-input.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Car Loan Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}