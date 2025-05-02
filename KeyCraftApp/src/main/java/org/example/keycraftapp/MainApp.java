package org.example.keycraftapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.keycraftapp.util.HibernateUtil;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/keycraftapp/fxml/LoginView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("KeyCraft - Iniciar sesión");
            stage.show();
        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Cerrar recursos de Hibernate al salir
        HibernateUtil.shutdown();
    }

    public static void main(String[] args) {
        launch();
    }
}