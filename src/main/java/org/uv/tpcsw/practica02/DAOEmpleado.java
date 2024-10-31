package org.uv.tpcsw.practica02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmpleado implements IDaoGeneral<Empleado, String> {

    @Override
    public boolean save(Empleado pojo) {
        
        ConexionDB con = ConexionDB.getInstance();
        
        TransaccionDB<Empleado> transaction = new TransaccionDB<Empleado>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="insert into empleados (clave, nombre, direccion, telefono) values"
                            + " (?,?,?,?)";
                    
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getClave());
                    pst.setString(2, pojo.getNombre());
                    pst.setString(3, pojo.getDireccion());
                    pst.setString(4, pojo.getTelefono());
                    return pst.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        return con.execute(transaction);
    }

    @Override
    public boolean delete(String id) {
        
        ConexionDB con = ConexionDB.getInstance();
        
        Empleado pojo = new Empleado();
        pojo.setClave(id);
        
        TransaccionDB<Empleado> transactionDelete = new TransaccionDB<Empleado>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "delete from empleados where clave = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getClave());
                    return pst.executeUpdate() > 0;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        return con.execute(transactionDelete);
    }

    @Override
    public boolean update(Empleado pojo, String id) {
        
        ConexionDB con = ConexionDB.getInstance();
        TransaccionDB<Empleado> transactionUpdate = new TransaccionDB<Empleado>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "update empleados set nombre = ?, direccion = ?, telefono = ? WHERE clave = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getNombre());
                    pst.setString(2, pojo.getDireccion());
                    pst.setString(3, pojo.getTelefono());
                    pst.setString(4, pojo.getClave());
                    return pst.executeUpdate() > 0;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        return con.execute(transactionUpdate);
    }

    @Override
    public List<Empleado> findAll() {
        
        ConexionDB con = ConexionDB.getInstance();
        String sql = "select * from empleados";
        ResultSet rs = con.select(sql);
        
        List<Empleado> empleados = new ArrayList<>();
        
        try {
            while(rs.next()){
                Empleado empl = new Empleado();
                empl.setClave(rs.getString("clave"));
                empl.setNombre(rs.getString("nombre"));
                empl.setDireccion(rs.getString("direccion"));
                empl.setTelefono(rs.getString("telefono"));
                
                empleados.add(empl);
            }
            return empleados;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Empleado findById(String id) {

        ConexionDB con = ConexionDB.getInstance();
        String sql = "select * from empleados where clave = '" + id + "'";
        ResultSet rs = con.select(sql);
        Empleado empl = new Empleado();
        
        try {
        
            if(rs.next()){
                empl.setClave(rs.getString("clave"));
                empl.setNombre(rs.getString("nombre"));
                empl.setDireccion(rs.getString("direccion"));
                empl.setTelefono(rs.getString("telefono"));
            }
            return empl;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
