module org.example.keycraftapp {

    /* ── JavaFX ─────────────────────────────── */
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    /* ── UI extra ───────────────────────────── */
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    /* ── Persistencia ───────────────────────── */
    requires org.hibernate.orm.core;   // hibernate‑core
    requires jakarta.persistence;      // @Entity, @Id, ...

    /* ── JDBC (driver MySQL) ────────────────── */
    requires java.sql;
    requires org.slf4j;

    /* ── JNDI (si lo necesitas) ────────────────── */
    //requires java.naming;  // Esto es solo si realmente necesitas JNDI.

    /* ── Apertura de paquetes para reflexión ── */
    opens org.example.keycraftapp            to javafx.fxml;                // clase MainApp, etc.
    opens org.example.keycraftapp.entities   to org.hibernate.orm.core, javafx.base;  // entidades
    opens org.example.keycraftapp.view.controller to javafx.fxml;           // controladores FXML

    /* ── Exporta el paquete principal ───────── */
    exports org.example.keycraftapp;
}
