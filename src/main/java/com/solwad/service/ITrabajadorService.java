package com.solwad.service;

import com.solwad.model.Trabajador;
import java.util.List;

public interface ITrabajadorService extends ICRUDService<Trabajador,String>{
    public List<Trabajador> ReportTrabajador(String nombre);
}
