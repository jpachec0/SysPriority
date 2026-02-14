package br.com.syspriority.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.syspriority.model.Consulta;
import br.com.syspriority.repository.ConsultaRepository;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta salvarConsulta(Consulta consulta) {
        if(consulta.getIdConsulta() != null && consultaRepository.existsById(consulta.getIdConsulta())) {
            Consulta existente = consultaRepository.findById(consulta.getIdConsulta())
                    .orElseThrow(() -> new RuntimeException("Erro ao achar consulta para atualização"));

            existente.setDataConsulta(consulta.getDataConsulta());
            existente.setHoraConsulta(consulta.getHoraConsulta());
            existente.setIdPacienteConsulta(consulta.getIdPacienteConsulta());
            existente.setIdMedicoConsulta(consulta.getIdMedicoConsulta());

            return consultaRepository.save(existente);
        } else {
            return consultaRepository.save(consulta);
        }
    }
    
    public void ExcluirConsulta(Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consulta não encontrada para exclusão");
        }
    }

    public Consulta buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

}
