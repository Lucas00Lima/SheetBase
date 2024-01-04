package com.example.Functions;

import com.example.LogTex;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InsertQuery {
    private static final List<String> listaDefault = new ArrayList<>();
    private String cacheInsert = null;
    public String insert(String table, Connection connection) {
        try {
            if (cacheInsert == null) {
                DatabaseMetaData metaData = connection.getMetaData();
                StringBuilder insertQuery = new StringBuilder();
                List<String> columnNames = new ArrayList<>();
                ResultSet resultSet = metaData.getColumns(null, null, table, null);
                insertQuery.append("INSERT INTO ").append(table).append(" (");
                while (resultSet.next()) {
                    String columnName = resultSet.getString("COLUMN_NAME");
                    String itensListaDefault = "";
                    if (table.equals("product")) {
                        if (columnName.equals("id")) { continue; }
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("deleted_at")) { break; }
                    } else if (table.equals("category")) {
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("kitchen_notes")) { break; }
                    } else if (table.equals("material")) {
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("pack_amount")) { break; }
                    } else if (table.equals("client")) {
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("is_trainee")) { break; }
                    } else if (table.equals("service")) {
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("active")) { break; }
                    } else if (table.equals("supplier")) {
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("ie")) { break; }
                    } else if (table.equals("product_item")) {
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("include")) { break; }
                    } else if (table.equals("flavor_size_price")) {
                        columnNames.add(columnName);
                        getDefaultList().add(itensListaDefault);
                        if (columnName.equals("price")) { break; }
                    }
                }
                for (int i = 0; i < columnNames.size(); i++) {
                    insertQuery.append(columnNames.get(i));
                    if (i < columnNames.size() - 1) { insertQuery.append(","); }
                }
                insertQuery.append(")").append(" VALUES (");
                for (int i = 0; i < columnNames.size(); i++) {
                    insertQuery.append("?");
                    if (i < columnNames.size() - 1) { insertQuery.append(","); }
                }
                insertQuery.append(")");
                cacheInsert = insertQuery.toString();
            }
        } catch (Exception e) {
            LogTex.textError("Erro no insertQuery");
            LogTex.textError(String.valueOf(e));
        }
        return cacheInsert;
    }
    public static List<String> getDefaultList() {
        return listaDefault;
    }
}
