package org.example.Functions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.DataAcess;
import org.example.Query.CreateCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Get {
    private final Connection connection;

    public Get(Connection connection) throws SQLException {
        this.connection = connection;
    }

    final private DataFormatter dataFormatter = new DataFormatter();
    final private static Map<Integer, Integer> lastInternalCodeMap = new HashMap<>();

    public int getNextInternalCode(int categoryId) {
        int lastInternalCode = lastInternalCodeMap.getOrDefault(categoryId, 0);
        int internalCode = categoryId * 100 + lastInternalCode;
        if (lastInternalCode == 0) {
            internalCode = categoryId * 100;
        }
        lastInternalCodeMap.put(categoryId, lastInternalCode + 1);
        return internalCode;
    }

    public boolean consultExist(String code) throws SQLException {
        String query = "SELECT internal_code FROM product WHERE internal_code = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public String verifyInternalCode(Cell code, int categoryId) throws SQLException {
        String internalCode = dataFormatter.formatCellValue(code);
        if (internalCode == null || internalCode.isEmpty()) {
            internalCode = String.valueOf(getNextInternalCode(categoryId));
        } else {
            if (consultExist(internalCode)) {
                internalCode += 9;
                if (consultExist(internalCode)) {
                    internalCode += 99;
                    if (consultExist(internalCode)) {
                        internalCode += 999;
                    }
                }
            } else {
                return internalCode;
            }
        }
        return internalCode;
    }

    public int getLastInsertId(String table) throws SQLException {
        String query = "SELECT MAX(id) AS last_id FROM ";
        int totalId = -1;
        PreparedStatement queryInsertId = connection.prepareStatement(query + table);
        ResultSet resultSet = queryInsertId.executeQuery();
        if (resultSet.next()) {
            totalId = resultSet.getInt("last_id");
        }
        return totalId;
    }

    public int idGet() throws SQLException {
        int lastInsertedCategoryId = 0;
        String categoryIdQuery = "SELECT MAX(id) AS lastCategoryId FROM category";
        Statement categoryIdStatement = connection.createStatement();
        ResultSet categoryIdResult = categoryIdStatement.executeQuery(categoryIdQuery);
        if (categoryIdResult.next()) {
            lastInsertedCategoryId = categoryIdResult.getInt("lastCategoryId");
        }
        return lastInsertedCategoryId;
    }

    public int idGetPai(int lasRowid) throws SQLException {
        String categoryIdPai = "SELECT id FROM category WHERE father_id = 0 ORDER BY id DESC LIMIT 1";
        Statement idPai = connection.createStatement();
        ResultSet categoryResult = idPai.executeQuery(categoryIdPai);
        if (categoryResult.next()) {
            lasRowid = categoryResult.getInt("id");
        }
        return lasRowid;
    }

    public boolean getSubCategory(int rowIndex, Sheet sheet) {
        // Verifica caso a nextCategoryValue retorne com um valor, ele define que a
        // categoria atual é uma categoria Father e a debaixo é uma sub categoria
        DataFormatter dataFormatter = new DataFormatter();
        int lastRowIndex = sheet.getLastRowNum();
        if (rowIndex < lastRowIndex) {
            Row nextRow = sheet.getRow(rowIndex + 1);
            Cell nextCategoryCell = (nextRow != null) ? nextRow.getCell(0) : null;
            String nextCategoryValue = (nextCategoryCell != null) ? dataFormatter.formatCellValue(nextCategoryCell)
                    : "";
            return !nextCategoryValue.isEmpty();
        }
        return false;
    }

    public int stateId(Cell stateCell) throws SQLException {
        String state = dataFormatter.formatCellValue(stateCell);
        int id = 0;
        if (state.isEmpty()) {
            return id;
        }
        String sqlState = "select id from state where aux_name = '" + state + "' OR name = '" + state + "'";
        Statement stateId = connection.createStatement();
        ResultSet idResult = stateId.executeQuery(sqlState);
        if (idResult.next()) {
            id = idResult.getInt("id");
        }
        idResult.close();
        stateId.close();
        return id;
    }

    public int cityId(Cell cityCell) throws SQLException {
        String city = dataFormatter.formatCellValue(cityCell);
        int id = 0;
        if (city.isEmpty()) {
            return id;
        }
        String sqlCity = "select id from city where name = '" + city + "'";
        Statement cityId = connection.createStatement();
        ResultSet idResult = cityId.executeQuery(sqlCity);
        if (idResult.next()) {
            id = idResult.getInt("id");
        }
        idResult.close();
        cityId.close();
        return id;
    }

    public List<String> namesCombo() throws SQLException {
        List<String> idList = new ArrayList<>();
        String productCombo = "SELECT id FROM product WHERE type2 = 6";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(productCombo);
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            idList.add(id);
        }
        return idList;
    }

    public boolean isCombo(String idProduct) throws SQLException {
        List<String> idList = namesCombo();
        for (String id : idList) {
            if (idProduct.contains(id)) {
                return true;
            }
        }
        return false;
    }

    public int numberCategory(Cell categoriaCell, Cell categoryPrincipal) {
        int idCategory = 0;
        String category = dataFormatter.formatCellValue(categoriaCell);
        String query = "SELECT id FROM category WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idCategory = resultSet.getInt("id");
            } else {
                //Criação de categoria
                idCategory = createCategoryMain(categoriaCell, categoryPrincipal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception if needed
        }
        return idCategory;
    }
    public int createCategoryMain (Cell categoriaCell, Cell categoryPrincipal) throws SQLException {
        int idcategoryPrincipal = 0;
        CreateCategory createCategory = new CreateCategory();
        if (categoryPrincipal != null) {
            //Criação da categoria principal
            idcategoryPrincipal = createCategory.createCategory(connection, categoryPrincipal, 0, 1);
        }
        return createCategory.createCategory(connection, categoriaCell, idcategoryPrincipal, 0);
    }
}
