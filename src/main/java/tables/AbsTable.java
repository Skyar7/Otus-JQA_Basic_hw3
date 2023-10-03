package tables;

import db.DBConnector;
import db.IDBConnector;

import java.util.*;

public abstract class AbsTable implements ITable {

    protected IDBConnector dbConnector = null;
    private String tableName = "";
    private Map<String, String> columns = null;

    public AbsTable(String tableName, Map<String, String> columns) {
        dbConnector = new DBConnector();
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void create() {
    List<String> columnsStr = new ArrayList<>();

    for(Map.Entry<String, String> entry: columns.entrySet()) {
        if(entry.getKey().equals("id")) {
            columnsStr.add(String.format("%s serial primary key", entry.getKey()));
        } else {
            columnsStr.add(String.format("%s %s", entry.getKey(), entry.getValue()));
        }
    }

    dbConnector.execute(
        String.format("create table if not exists %s (%s);",
            this.tableName,
            String.join(", ", columnsStr)
        )
    );
    }

    @Override
    public void delete() {
    dbConnector.execute(String.format("drop table if exists %s;", this.tableName));
    }

    protected String convertColumnsTable(String... columns) {
        if(columns.length == 0) {
            return "*";
        }

    return String.join(", ", columns);
    }

    @Override
    public void insert(List data) {
        List<String> columns = new ArrayList<>();

        for (String key : this.columns.keySet()) {
            columns.add(key);
        }

        for (Object object: data) {
            dbConnector.execute(String.format("insert into %s (%s) values (%s);", tableName,
                String.join(", ", columns), object.toString()));
        }
    }
}