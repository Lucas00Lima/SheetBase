package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.Generetor.Generator;

public class HelloController {
    public AnchorPane containerMaster;
    public GridPane gridPane;
    public Button buttonSearch;
    public VBox vboxCheck;
    @FXML
    public CheckBox checkSupplier;
    @FXML
    private CheckBox checkProduct;
    @FXML
    public CheckBox updateSheet;
    @FXML
    private TextField inputLocal;
    @FXML
    private TextArea texLog;
    private String sheet;
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
            LogTex.textInfo("Selecionado a importação de produtos");
        } else if (checkSupplier.isSelected()) {
            executeLogicForCheckBox("supplier");
            LogTex.textInfo("Selecionado a importação de Fornecedor");
        } else {
            LogTex.textError("Nenhum CheckBox selecionado");
        }
    }
    private void executeLogicForCheckBox(String checkBoxName) {
        DataAcess dataAcess = new DataAcess();
            System.out.println(sheet);
            Generator generator = new Generator(dataAcess, checkBoxName, sheet);
        generator.generetor();
        System.out.println("Função associada ao CheckBox '" + checkBoxName + "' executada!");
    }
    @FXML
    protected void onSearchButtonClick() {
        DataAcess dataAcess = new DataAcess();
        sheet = dataAcess.accessSheet();
        inputLocal.setText(sheet);
        System.out.println("Botão de busca clicado!");
    }
    public void appendLog(String message, boolean isError) {
        String formattedMessage = message + "\n";
        if (isError) {
            texLog.setStyle("-fx-text-fill: red;");
        } else {
            texLog.setStyle("-fx-text-fill: black;");
        }
        texLog.appendText(formattedMessage);
    }
}