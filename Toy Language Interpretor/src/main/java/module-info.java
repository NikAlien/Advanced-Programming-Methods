module gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens gui to javafx.fxml;
    opens gui.dataTransferTableView to javafx.base;
    exports gui;
}