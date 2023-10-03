package tables;

import java.util.List;
import java.util.Map;

public interface ITable {
    void create();
    void delete();
    void insert(List data);
}
