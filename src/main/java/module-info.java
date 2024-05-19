module com.example.datasecurity {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.datasecurity to javafx.fxml;
    exports com.example.datasecurity;
}