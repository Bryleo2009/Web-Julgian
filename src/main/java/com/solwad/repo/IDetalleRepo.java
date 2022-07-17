package com.solwad.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.solwad.model.Detalle;
@Repository
@Transactional
public interface IDetalleRepo extends CrudRepository<Detalle, Integer>{
	@Modifying
	@Query(value="DELETE FROM detalle_comprobante", nativeQuery = true)
	void deleten();
	@Modifying
	@Query(value="alter table `solwad`.`detalle_comprobante` AUTO_INCREMENT=1;", nativeQuery = true)
	void reinicio();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`detalle_comprobante`\r\n"
			+ "(`cant_product_detalle`,\r\n"
			+ "`precio_total_detalle`,\r\n"
			+ "`precio_uni_detalle`,\r\n"
			+ "`producto_detalle`,\r\n"
			+ "`id_comp`)\r\n"
			+ "VALUES\r\n"
			+ "(2,259.80,129.90,'Pantalón Jogger Hombre Doo Australia','N00001');", nativeQuery = true)
	void prueba();
	@Query(value="SELECT * FROM `detalle_comprobante` WHERE id_comp = 'N00000'", nativeQuery = true)
	List<Detalle> listDetalle();
        @Query(value="SELECT * FROM `detalle_comprobante` WHERE id_comp LIKE ?1", nativeQuery = true)
	List<Detalle> lista_det_Comp(String id_comp);
        
        @Modifying
    	@Query(value="DELETE FROM detalle_comprobante WHERE id_comp = 'N00000'", nativeQuery = true)
    	void borrado(); 
        
}
