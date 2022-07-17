package com.solwad.service;

import com.solwad.model.Comprobante;
import java.util.List;

public interface IComprobanteService extends ICRUDService<Comprobante,String>{
    public List<Comprobante> ReportComprobante(String cliente);
    public List<Comprobante> CantidadVenta(int id);
}
