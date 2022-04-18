module com.nameapp {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.nameapp to javafx.fxml;

    exports com.nameapp;
}
