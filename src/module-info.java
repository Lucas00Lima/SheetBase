module com.example.frontsheebase {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.frontsheebase to javafx.fxml;
    exports com.example.frontsheebase;
}