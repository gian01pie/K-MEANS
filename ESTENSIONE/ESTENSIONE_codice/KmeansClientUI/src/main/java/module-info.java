module com.map.kmeansclientui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.map.kmeansclientui to javafx.fxml;
    exports com.map.kmeansclientui;
    exports com.map.kmeansclientui.model;
    opens com.map.kmeansclientui.model to javafx.fxml;
}