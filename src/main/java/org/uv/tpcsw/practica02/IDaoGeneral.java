package org.uv.tpcsw.practica02;

// <>

import java.util.List;

interface IDaoGeneral<T, ID> {
    
   public boolean save(T pojo);
   
   public boolean delete(ID id);
   
   public boolean update(T pojo, ID id);
   
   public List<T> findAll();
   
   public T findById(ID id);
    
}
