package com.example;

import com.example.alert.Error;
import com.example.generetor.Generator;
import com.example.generetor.GeneratorUpdate;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLException;

//TODO: Retirado o supplier e updateSheet
public class HelloController {
    public AnchorPane containerMaster;
    public GridPane gridPane;
    public Button buttonSearch;
    public VBox vboxCheck;
    public ScrollPane scrollPane;
    public Button buttonUpdate;
    public Button buttonCreate;
    private String filePath;
    @FXML
    private CheckBox checkProduct;
    @FXML
    private TextField inputLocal;
    @FXML
    private TextFlow texLog;
    @FXML
    private void initialize() {
        addSelectionListener(checkProduct);
        LogTex.setFrontendController(this);
    }
    private void addSelectionListener(CheckBox checkBox) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (checkBox != checkProduct) {checkProduct.setSelected(false); }
            }
        });
    }
    @FXML
    protected void onExecuteButtonClick() {
        if (checkProduct.isSelected()) {
            executeLogicForCheckBox("product");
        } else {
            Error alert = new Error();
            alert.Alerta("Nenhum CheckBox selecionado");
            LogTex.textError("Selecione um checkBox");
        }
    }
    @FXML
    protected void oncheckUpdate() throws SQLException, IOException {
        DataAcess dataAcess = new DataAcess();
        GeneratorUpdate update =  new GeneratorUpdate(dataAcess, filePath);
        update.UpdateProduct();
    }
    private void executeLogicForCheckBox(String checkBoxName) {
        DataAcess dataAcess = new DataAcess();
        Generator generator = new Generator(dataAcess, checkBoxName, filePath);
        generator.generetor();
    }
    @FXML
    protected void onSearchButtonClick() {
        DataAcess dataAcess = new DataAcess();
        filePath = dataAcess.accessSheet();
        if (filePath == null) {
            Error alert = new Error();
            alert.Alerta("Selecione uma planilha");
        }
        inputLocal.setText(filePath);
    }
    public void appendLog(String message, boolean isError) {
        Text logText = new Text(message + "\n");
        if (isError) { logText.setFill(Color.RED); }
        texLog.getChildren().add(logText);
        texLog.getChildren().add(new Text("\n"));
        Platform.runLater(() -> {scrollPane.setVvalue(1.0);});
    }
}