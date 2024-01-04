package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        scene.getStylesheets().add(getClass().getResource("/com/example/style.css").toExternalForm());
        stage.setTitle("Criação de base");
        stage.setScene(scene);
        stage.show();
        centerStage(stage);
    }
    private void centerStage(Stage stage) {
        // Obtendo o tamanho da tela
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Calculando as coordenadas para centralizar a janela
        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2;
        double centerY = bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2;

        // Configurando a posição da janela
        stage.setX(centerX);
        stage.setY(centerY);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}