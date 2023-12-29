package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.io.*;
import java.util.Properties;

public class HelloController {
    @FXML
    private TextArea texLog;
    @FXML
    private CheckBox chekProduct;
    private Properties properties;
    @FXML
    private TextField inputLocal;
    @FXML
    private void initialize() {
        // Carrega as configurações salvas
        loadSettings();
    }
    private void loadSettings() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            // Define o estado do CheckBox com base nas configurações carregadas
            chekProduct.setSelected(Boolean.parseBoolean(properties.getProperty("chekProduct", "false")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onExecuteButtonClick() {
        // Verifica se o CheckBox está marcado antes de executar a função
        if (chekProduct.isSelected()) {
            // Coloque aqui a lógica que você deseja executar
            System.out.println("Função associada ao CheckBox executada!");
        }
    }
    @FXML
    protected void onSearchButtonClick() {
        DataAcess dataAcess = new DataAcess();
        String sheet = dataAcess.accessSheet();
        inputLocal.appendText(sheet);
        System.out.println("Botão de busca clicado!");
    }
    @FXML
    protected void onSaveSettingsButtonClick() {
        // Salva as configurações, incluindo o estado do CheckBox
        saveSettings();
    }
    private void saveSettings() {
        properties.setProperty("chekProduct", String.valueOf(chekProduct.isSelected()));
        try (OutputStream output = new FileOutputStream("config.properties")) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}