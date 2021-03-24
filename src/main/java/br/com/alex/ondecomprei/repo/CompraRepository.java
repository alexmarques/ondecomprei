package br.com.alex.ondecomprei.repo;

import br.com.alex.ondecomprei.models.Compra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompraRepository extends CrudRepository<Compra, Long> {
    @Query("from Compra c where c.usuario.email = :email")
    List<Compra> findAllByEmailUsuario(String email);

}
