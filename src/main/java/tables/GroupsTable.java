package tables;

import dataobj.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GroupsTable extends AbsTable {

    private static final String NAME = "groups";

    private static final Map<String, String> COLUMNS = new LinkedHashMap(){{
        put("id", "");
        put("name", "TEXT");
        put("id_curator", "int");
    }};

    public GroupsTable() {
        super(NAME, COLUMNS);
    }
}