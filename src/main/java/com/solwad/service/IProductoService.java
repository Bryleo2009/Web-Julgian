package com.solwad.service;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.solwad.model.Producto;
import java.util.List;

public interface IProductoService extends ICRUDService<Producto,Integer>{
    public List<Producto> ReportProduct(String nombre, String cate);
}
