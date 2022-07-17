package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.Comprobante;
import java.util.List;
@Repository
@Transactional
public interface IComprobanteRepo extends CrudRepository<Comprobante, String>{
	@Modifying
	@Query(value="DELETE FROM comprobante", nativeQuery = true)
	void deleten();
	@Modifying
	@Query(value="alter table `solwad`.`comprobante` AUTO_INCREMENT=1;", nativeQuery = true)
	void reinicio();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`comprobante`\r\n"
			+ "(`id_comp`,`fecha_emi_comp`,\r\n"
			+ "`ident_client_comp`,\r\n"
			+ "`monto_subtotal_comp`,\r\n"
			+ "`monto_total_comp`,\r\n"
			+ "`nom_client_comp`,\r\n"
			+ "`id_tc`,\r\n"
			+ "`id_tp`,\r\n"
			+ "`id_user`)\r\n"
			+ "VALUES\r\n"
			+ "('N00000','2000-01-01','00000000',1,1,'NULL',1,1,1);", nativeQuery = true)
	void nulo();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`comprobante`\r\n"
			+ "(`id_comp`,`fecha_emi_comp`,\r\n"
			+ "`ident_client_comp`,\r\n"
			+ "`monto_subtotal_comp`,\r\n"
			+ "`monto_total_comp`,\r\n"
			+ "`nom_client_comp`,\r\n"
			+ "`id_tc`,\r\n"
			+ "`id_tp`,\r\n"
			+ "`id_user`)\r\n"
			+ "VALUES\r\n"
			+ "('N00001','2000-08-24','107185092608',129.90,259.80,'Of System sac',1,1,2);", nativeQuery = true)
	void prueba();
        
    @Query(value = "SELECT * FROM comprobante C WHERE C.nom_client_comp LIKE %?1%",
            nativeQuery = true)
    List<Comprobante> ReportComprobante(String cliente);
	
    @Query(value="SELECT * FROM comprobante"
                   + " INNER JOIN usuario ON comprobante.id_user = usuario.id_user"
                   + " WHERE comprobante.id_user = ?1", nativeQuery = true)
        List<Comprobante> cantidadVentaxUsuario(int id_user);
}
