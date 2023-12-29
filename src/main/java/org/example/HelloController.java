package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
public class HelloController {
    public AnchorPane containerMaster;
    public GridPane gridPane;
    public Button buttonSearch;
    public VBox vboxCheck;
    @FXML
    public CheckBox chekSupplier;
    @FXML
    public CheckBox chekMaterial;
    @FXML
    private CheckBox chekProduct;
    @FXML
    public CheckBox updateSheet;
    @FXML
    private TextField inputLocal;
    @FXML
    private void initialize() {
        addSelectionListener(chekProduct);
        addSelectionListener(chekSupplier);
        addSelectionListener(chekMaterial);
    }
    private void addSelectionListener(CheckBox checkBox) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Desmarca os outros CheckBox
                if (checkBox != chekProduct) {
                    chekProduct.setSelected(false);
                }
                if (checkBox != chekSupplier) {
                    chekSupplier.setSelected(false);
                }
                if (checkBox != chekMaterial) {
                    chekMaterial.setSelected(false);
                }
            }
        });
        }
    @FXML
    protected void onExecuteButtonClick() {
        if (chekProduct.isSelected()) {
            executeLogicForCheckBox("Product");
        } else if (chekSupplier.isSelected()) {
            executeLogicForCheckBox("Supplier");
        } else if (chekMaterial.isSelected()) {
            executeLogicForCheckBox("Material");
        } else {
            System.out.println("Nenhum CheckBox selecionado.");
        }
    }
    private void executeLogicForCheckBox(String checkBoxName) {
        // Coloque a lógica específica para cada CheckBox aqui
        System.out.println("Função associada ao CheckBox '" + checkBoxName + "' executada!");
    }
    @FXML
    protected void onSearchButtonClick() {
        DataAcess dataAcess = new DataAcess();
        String sheet = dataAcess.accessSheet();
        inputLocal.setText(sheet);
        System.out.println("Botão de busca clicado!");
    }
}