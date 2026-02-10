package br.com.syspriority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.syspriority.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
