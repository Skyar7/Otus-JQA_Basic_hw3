package tables;

import dataobj.Curator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CuratorsTable extends AbsTable {

    private static final String NAME = "curators";

    private static final Map<String, String> COLUMNS = new LinkedHashMap(){{
        put("id", "");
        put("fio", "TEXT");
    }};

    public CuratorsTable() {
        super(NAME, COLUMNS);
    }
}