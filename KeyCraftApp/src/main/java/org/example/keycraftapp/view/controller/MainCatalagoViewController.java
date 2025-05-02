package org.example.keycraftapp.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainCatalagoViewController {

    @FXML
    private Label label;

    @FXML
    private Button button;

    @FXML
    private void initialize() {
        //Metodo que se llama automáticamente al cargar el FXML
        label.setText("¡Bienvenido al catálogo!");

        button.setOnAction(event -> {
            label.setText("¡Botón clicado!");
        });
    }
}
