import db.DBConnector;
import db.IDBConnector;
import tables.AbsTable;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RequestUtils {
    AbsTable studentsTable = new StudentsTable();
    AbsTable curatorsTable = new CuratorsTable();
    AbsTable groupsTable = new GroupsTable();
    IDBConnector dbConnector = new DBConnector();

    public RequestUtils() {
    }

    public void createTables() {
        studentsTable.create();
        curatorsTable.create();
        groupsTable.create();
    }

    public void waitingForEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Введите любой символ для закрытия программы.");
        scanner.nextLine();
    }

    // Заполнение таблиц данными.
    public void insertData() {
        studentsTable.insert(new ArrayList<String>() {{
            add("'1', 'Chesnokov Vadim Aleksandrovich', 'M', '1'");
            add("'2', 'Voroncova Kira Matveevna', 'F', '1'");
            add("'3', 'Nikulina Olga Vladislavovna', 'F', '1'");
            add("'4', 'Grigoriev Andrey Nikitich', 'M', '1'");
            add("'5', 'Yakovlev Kirill Evgenyevich', 'M', '1'");
            add("'6', 'Popova Daria Mihaylovna', 'F', '2'");
            add("'7', 'Smirnov Artem Vladimirovich', 'M', '2'");
            add("'8', 'Rakova Alisa Dmitrievna', 'F', '2'");
            add("'9', 'Suharev Artem Egorovich', 'M', '2'");
            add("'10', 'Chernoushko Alisa Mihaylovna', 'F', '2'");
            add("'11', 'Karpov Miron Dmitrievich', 'M', '3'");
            add("'12', 'Golubev Anatoliy Demidovich', 'M', '3'");
            add("'13', 'Medvedeva Polina Aleksandrovna', 'F', '3'");
            add("'14', 'Grigorieva Diana Stepanovna', 'F', '3'");
            add("'15', 'Malisheva Sofia Arkadievna', 'F', '3'");
        }});

        curatorsTable.insert(new ArrayList<String>() {{
            add("'1', 'Lavrentiev Fedor Zaharovich'");
            add("'2', 'Galkin Lev Egorovich'");
            add("'3', 'Guseva Kseniya Artemovna'");
            add("'4', 'Petrov Maksin Denisovich'");
        }});

        groupsTable.insert(new ArrayList<String>() {{
            add("'1', 'A', '1'");
            add("'2', 'B', '2'");
            add("'3', 'C', '3'");
        }});
    }

    // Вывод информации о всех студентах с названием группы и именем куратора.
    public void allStudsWithGroupsAndCurators() throws SQLException {

        String selectStudentsQuery = "SELECT " + studentsTable.getTableName() + ".fio, "
                + groupsTable.getTableName() + ".name, "
                + curatorsTable.getTableName() + ".fio " +
                "FROM " + studentsTable.getTableName() +
                " JOIN " + groupsTable.getTableName() + " ON " + studentsTable.getTableName() + ".id_group = " + groupsTable.getTableName() + ".id" +
                " JOIN " + curatorsTable.getTableName() + " ON " + groupsTable.getTableName() + ".id_curator = " + curatorsTable.getTableName() + ".id;";
        ResultSet sqlResult = dbConnector.executeQuery(selectStudentsQuery);
        System.out.println();
        System.out.println("Данные всех студентов:");

        while (sqlResult.next()) {
            String studentName = sqlResult.getString(1);
            String groupName = sqlResult.getString(2);
            String curatorName = sqlResult.getString(3);
            System.out.println(studentName + ", группа: " + groupName + ", куратор: " + curatorName);
        }
    }

    // Вывод количества студентов.
    public void showStudentsCount() throws SQLException {

        String countStudentsQuery = "SELECT COUNT(*) FROM " + studentsTable.getTableName();
        ResultSet sqlResult = dbConnector.executeQuery(countStudentsQuery);

        if (sqlResult.next()) {
            int count = sqlResult.getInt(1);
            System.out.println();
            System.out.println("Количество студентов: " + count);
        }
    }

    // Вывод студенток.
    public void showFemStudents() throws SQLException {

        String selectFemaleStudentsQuery = "SELECT fio FROM " + studentsTable.getTableName() + " WHERE sex = 'F'";
        ResultSet sqlResult = dbConnector.executeQuery(selectFemaleStudentsQuery);
        System.out.println();
        System.out.println("Список студенток:");

        while (sqlResult.next()) {
            System.out.println(sqlResult.getString(1));
        }
    }

    // Обновление данных по группе.
    public void updateGroupsData() throws SQLException {

        String requiredGroupToChangeCurator = "C";
        String updateGroupQuery = "UPDATE " + groupsTable.getTableName() + " SET id_curator = 4 WHERE name = '" +
                requiredGroupToChangeCurator + "';";
        dbConnector.execute(updateGroupQuery);
        System.out.println();
        System.out.println("Смена куратора в группе " + requiredGroupToChangeCurator);
    }

    // Вывод списка групп с их кураторами.
    public void showGroupsWithCurators() throws SQLException {

        String selectGroupsQuery = "SELECT " + groupsTable.getTableName() + ".name, " + curatorsTable.getTableName() + ".fio " +
                "FROM " + groupsTable.getTableName() +
                " JOIN " + curatorsTable.getTableName() +
                " ON " + groupsTable.getTableName() + ".id_curator = " + curatorsTable.getTableName() + ".id;";
        ResultSet sqlResult = dbConnector.executeQuery(selectGroupsQuery);
        System.out.println();
        System.out.println("Список групп с их кураторами:");

        while (sqlResult.next()) {
            String groupName = sqlResult.getString(1);
            String curatorName = sqlResult.getString(2);
            System.out.println("Группа: " + groupName + ", Куратор: " + curatorName);
        }
    }

    // Вывод студентов из определенной группы.
    public void showStudsFromGroup() throws SQLException {
        String requiredGroup = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Введите из какой группы вывести список студентов:");
        String input = scanner.nextLine().trim().toUpperCase();

        while (!input.equals("A") && !input.equals("B") && !input.equals("C")) {
            System.out.println("Неверное значение. Введите корректное название группы (A, B или C:)");
            input = scanner.nextLine().trim().toUpperCase();
        }

        requiredGroup = input;
        String selectStudentsByGroupQuery = "SELECT fio FROM " + studentsTable.getTableName() +
                " WHERE id_group IN (SELECT id FROM " + groupsTable.getTableName() + " WHERE name = '" + requiredGroup + "');";

        ResultSet resultSet = dbConnector.executeQuery(selectStudentsByGroupQuery);
        System.out.println();
        System.out.println("Список студентов из группы " + requiredGroup + ":");

        while (resultSet.next()) {
            String studentName = resultSet.getString(1);
            System.out.println(studentName);
        }
    }

    public void clearDB() {
        studentsTable.delete();
        curatorsTable.delete();
        groupsTable.delete();
    }
}