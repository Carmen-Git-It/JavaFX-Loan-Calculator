module com.carmengitit.ws01 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.carmengitit.ws01 to javafx.fxml;
    exports com.carmengitit.ws01;
}