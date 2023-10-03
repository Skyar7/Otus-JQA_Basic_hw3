package db;

import java.sql.ResultSet;

public interface IDBConnector {
    void execute(String sqlRequest);
    ResultSet executeQuery(String sqlRequest);
}
