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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idConsulta;

    @Column(nullable = false)
    private String dataConsulta;

    @Column(nullable = false)
    private String horaConsulta;

    @Column(nullable = false)
    private String motivoConsulta;

    @Column(nullable = true)
    private String descricaoDetalhadaConsulta;

    @Column(nullable = false)
    private Long idPacienteConsulta;

    @Column(nullable = false)
    private Long idMedicoConsulta;

}
