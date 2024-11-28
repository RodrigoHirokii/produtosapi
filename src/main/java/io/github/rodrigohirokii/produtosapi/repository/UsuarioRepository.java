package io.github.rodrigohirokii.produtosapi.repository;

import io.github.rodrigohirokii.produtosapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByLogin(String login);
}
