package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import org.example.Generetor.Generator;
import javafx.scene.text.Text;

public class HelloController {
    public AnchorPane containerMaster;
    public GridPane gridPane;
    public Button buttonSearch;
    public VBox vboxCheck;
    public ScrollPane scrollPane;
    private String sheet;
    @FXML
    public CheckBox checkSupplier;
    @FXML
    private CheckBox checkProduct;
    @FXML
    public CheckBox updateSheet;
    @FXML
    private TextField inputLocal;
    @FXML
    private TextFlow texLog;
    @FXML
    private void initialize() {
        addSelectionListener(checkProduct);
        addSelectionListener(checkSupplier);
        LogTex.setFrontendController(this);
    }
    private void addSelectionListener(CheckBox checkBox) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Desmarca os outros CheckBox
                if (checkBox != checkProduct) {
                    checkProduct.setSelected(false);
                }
                if (checkBox != checkSupplier) {
                    checkSupplier.setSelected(false);
                }
            }
        });
    }
    @FXML
    protected void onExecuteButtonClick() {
        if (checkProduct.isSelected()) {
            executeLogicForCheckBox("product");
        } else if (checkSupplier.isSelected()) {
            executeLogicForCheckBox("supplier");
        } else {
            LogTex.textError("Nenhum CheckBox selecionado");
        }
    }
    private void executeLogicForCheckBox(String checkBoxName) {
        DataAcess dataAcess = new DataAcess();
        System.out.println(sheet);
        Generator generator = new Generator(dataAcess, checkBoxName, sheet);
        generator.generetor();
    }
    @FXML
    protected void onSearchButtonClick() {
        DataAcess dataAcess = new DataAcess();
        sheet = dataAcess.accessSheet();
        inputLocal.setText(sheet);
    }
    public void appendLog(String message, boolean isError) {
        Text logText = new Text(message + "\n");
        if (isError) { logText.setFill(Color.RED); }
        texLog.getChildren().add(logText);
        texLog.getChildren().add(new Text("\n"));
        Platform.runLater(() -> {
            // Rola para a última linha
            scrollPane.setVvalue(1.0);
        });
    }
}