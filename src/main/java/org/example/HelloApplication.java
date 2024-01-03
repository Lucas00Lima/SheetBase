package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/frontsheebase/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        stage.show();
        stage.setTitle("Criação de base");
        centerStage(stage);
        stage.setScene(scene);
        stage.show();
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