package br.com.syspriority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.syspriority.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
}
