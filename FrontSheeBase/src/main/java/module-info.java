module com.example.frontsheebase {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.frontsheebase to javafx.fxml;
    exports com.example.frontsheebase;
}