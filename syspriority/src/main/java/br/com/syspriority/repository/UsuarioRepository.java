package br.com.syspriority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.syspriority.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
