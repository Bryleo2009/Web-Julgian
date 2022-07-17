package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.Producto;
import java.util.List;

@Transactional
@Repository
public interface IProductoRepo extends CrudRepository<Producto, Integer> {

    @Modifying
    @Query(value = "DELETE FROM Producto", nativeQuery = true)
    void deleteproducto();

    @Modifying
    @Query(value = "alter table `solwad`.`producto` AUTO_INCREMENT=1;", nativeQuery = true)
    void reinicioproducto();

    @Modifying
    @Query(value = "INSERT INTO `solwad`.`producto`\r\n"
            + "(`descripcion_product`,\r\n"
            + "`precio_uni`,\r\n"
            + "`stock_product`,\r\n"
            + "`talla_product`,\r\n"
            + "`id_categ`)\r\n"
            + "VALUES\r\n"
            + "('NULL',1,1,1,1);", nativeQuery = true)
    void nulo();

    @Modifying
    @Query(value = "INSERT INTO `solwad`.`producto`\r\n"
            + "(`descripcion_product`,\r\n"
            + "`precio_uni`,\r\n"
            + "`stock_product`,\r\n"
            + "`talla_product`,\r\n"
            + "`id_categ`)\r\n"
            + "VALUES\r\n"
            + "('Pantalón Jogger Hombre Doo Australia',129.90,12,32,2);", nativeQuery = true)
    void prueba();

    @Query(value = "SELECT * FROM producto P INNER JOIN categoria_product C ON C.id_categ = P.id_categ WHERE P.descripcion_product LIKE %?1% AND C.nombre_categ LIKE %?2%",
            nativeQuery = true)
    List<Producto> ReportProduct(String nombre, String cate);

}
