package com.example;

import com.example.Alert.Error;
import com.example.Generetor.Generator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
//TODO: Retirado o supplier e updateSheet
public class HelloController {
    public AnchorPane containerMaster;
    public GridPane gridPane;
    public Button buttonSearch;
    public VBox vboxCheck;
    public ScrollPane scrollPane;
    private String sheet;
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
                if (checkBox != checkProduct) { checkProduct.setSelected(false); }
//                if (checkBox != checkSupplier) {
//                    checkSupplier.setSelected(false);
//                }
            }
        });
    }
    @FXML
    protected void onExecuteButtonClick() {
        if (checkProduct.isSelected()) {
            executeLogicForCheckBox("product");
//        } else if (checkSupplier.isSelected()) {
//            executeLogicForCheckBox("supplier");
        } else {
            Error alert = new Error();
            alert.Alerta("Nenhum CheckBox selecionado");
            LogTex.textError("Selecione um checkBox");
        }
    }
    private void executeLogicForCheckBox(String checkBoxName) {
        DataAcess dataAcess = new DataAcess();
        Generator generator = new Generator(dataAcess, checkBoxName, sheet);
        generator.generetor();
    }
    @FXML
    protected void onSearchButtonClick() {
        DataAcess dataAcess = new DataAcess();
        sheet = dataAcess.accessSheet();
        if (sheet == null) {
            Error alert = new Error();
            alert.Alerta("Selecione uma planilha");
        }
        inputLocal.setText(sheet);
    }
    public void appendLog(String message, boolean isError) {
        Text logText = new Text(message + "\n");
        if (isError) { logText.setFill(Color.RED); }
        texLog.getChildren().add(logText);
        texLog.getChildren().add(new Text("\n"));
        Platform.runLater(() -> {scrollPane.setVvalue(1.0);});
    }
}