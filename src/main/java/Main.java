import db.DBConnector;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        RequestUtils request = new RequestUtils();

        try {
            request.clearDB();
            request.createTables();
            request.insertData();
            request.allStudsWithGroupsAndCurators();
            request.showStudentsCount();
            request.showFemStudents();
            request.showGroupsWithCurators();
            request.updateGroupsData();
            request.showGroupsWithCurators();
            request.showStudsFromGroup();
            request.waitingForEnter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            request.clearDB();
            DBConnector.close();
            System.out.println("Работа программы завершена.");
        }
    }
}