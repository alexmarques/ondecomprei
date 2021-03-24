package br.com.alex.ondecomprei.repo;

import br.com.alex.ondecomprei.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
}
