package br.com.syspriority.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.syspriority.model.Paciente;
import br.com.syspriority.repository.PacienteRepository;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente salvarPaciente(Paciente paciente) {
        if(paciente.getIdPaciente() != null && pacienteRepository.existsById(paciente.getIdPaciente())) {
            Paciente existente = pacienteRepository.findById(paciente.getIdPaciente())
                    .orElseThrow(() -> new RuntimeException("Erro ao achar paciente para atualização"));

            existente.setNomePaciente(paciente.getNomePaciente());
            existente.setCpfPaciente(paciente.getCpfPaciente());
            existente.setNomePaciente(paciente.getNomePaciente());
            existente.setTipoSanguineoPaciente(paciente.getTipoSanguineoPaciente());
            existente.setAlturaPaciente(paciente.getAlturaPaciente());

            return pacienteRepository.save(existente);
        } else {
            return pacienteRepository.save(paciente);
        }
    }
public void ExcluirPaciente(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paciente não encontrado para exclusão");
        }
    }

    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

}
