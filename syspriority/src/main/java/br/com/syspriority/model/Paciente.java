package br.com.syspriority.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idPaciente;

    @Column(nullable = false, unique = true)
    private String cpfPaciente;

    @Column(nullable = false)
    private String nomePaciente;

    @Column(nullable = true)
    private String tipoSanguineoPaciente;

    @Column(nullable = true)
    private Long alturaPaciente;

    @Column(nullable = false)
    private Long idadePaciente;
}
