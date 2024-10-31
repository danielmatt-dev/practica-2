package org.uv.tpcsw.practica02;

import java.sql.Connection;

public abstract class TransaccionDB<T> {
    
    protected T pojo;
    
    protected TransaccionDB(T pojo) {
        this.pojo = pojo;
    }
    
    public abstract boolean execute(Connection con);
    
}
