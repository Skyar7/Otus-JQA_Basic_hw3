package tables;

import dataobj.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentsTable extends AbsTable {
    private static final String NAME = "students";

    private static final Map<String, String> COLUMNS = new LinkedHashMap(){{
        put("id", "");
        put("fio", "TEXT");
        put("sex", "TEXT");
        put("id_group", "int");
    }};

    public StudentsTable() {
        super(NAME, COLUMNS);
    }
}