package br.com.syspriority.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.syspriority.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    List<Usuario> findByTipoUsuario(String tipoUsuario);

    List<Usuario> findByDisponibilidadeUsuarioTrueAndTipoUsuario(String tipoUsuario);

}
