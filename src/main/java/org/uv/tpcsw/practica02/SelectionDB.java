package org.uv.tpcsw.practica02;

import java.sql.Connection;
import java.util.List;

public abstract class SelectionDB<T, ID> {
    
    protected abstract List<T> executeAll(Connection con);
    
    protected abstract T execute(Connection con, ID id);
    
}
