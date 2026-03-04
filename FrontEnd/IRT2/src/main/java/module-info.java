module org.esgis.irt2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.sql;

    opens org.esgis.irt2 to javafx.fxml;
    exports org.esgis.irt2;
    exports org.esgis.irt2.Controller;
    opens org.esgis.irt2.Controller to javafx.fxml;
}