module za.co.ohlukilebanking.banking_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens za.co.ohlukilebanking.banking_app to javafx.fxml;
    exports za.co.ohlukilebanking.banking_app;
}