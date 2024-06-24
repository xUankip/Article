module com.example.article {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.article to javafx.fxml;
    exports com.example.article;
    exports com.example.article.entity;
    exports com.example.article.controller;
    opens com.example.article.controller to javafx.fxml;
}