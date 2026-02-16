package br.com.syspriority.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.syspriority.model.Consulta;
import br.com.syspriority.model.Usuario;
import br.com.syspriority.repository.ConsultaRepository;

@Service
public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;

    private final PacienteService pacienteService;

    private final UsuarioService usuarioService;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteService pacienteService, UsuarioService usuarioService) {
        this.consultaRepository = consultaRepository;
        this.pacienteService = pacienteService;
        this.usuarioService = usuarioService;
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Consulta salvarConsulta(Consulta consulta) {
        if(consulta.getIdConsulta() != null && consultaRepository.existsById(consulta.getIdConsulta())) {
            Consulta existente = consultaRepository.findById(consulta.getIdConsulta())
                    .orElseThrow(() -> new RuntimeException("Erro ao achar consulta para atualização"));

            existente.setDataConsulta(consulta.getDataConsulta());
            existente.setHoraConsulta(consulta.getHoraConsulta());
            existente.setCpfPacienteConsulta(consulta.getCpfPacienteConsulta());
            existente.setCrmMedicoConsulta(consulta.getCrmMedicoConsulta());
            existente.setGravidadeConsulta(consulta.getGravidadeConsulta());
            existente.setMotivoConsulta(consulta.getMotivoConsulta());
            existente.setDescricaoDetalhadaConsulta(consulta.getDescricaoDetalhadaConsulta());

            return consultaRepository.save(existente);
        } else {
            return consultaRepository.save(consulta);
        }
    }
    
    public void excluirConsulta(Long id) {
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

    public Long calcularScoreConsulta(Consulta consulta) {
        Long score = 0L;

        score += consulta.getGravidadeConsulta() * 10;

        Long idadePaciente = pacienteService.buscarPacientePorCpf(consulta.getCpfPacienteConsulta()).getIdadePaciente();
        if (idadePaciente >= 60) {
            score += 15;
        } else if (idadePaciente <= 12) {
            score += 10;
        } else {
            score += 5;
        }

        return score;
    }

    public List<Consulta> listarConsultasOrdenadasPorScore() {
        List<Consulta> consultas = consultaRepository.findAll();
        consultas.sort((c1, c2) -> calcularScoreConsulta(c2).compareTo(calcularScoreConsulta(c1)));
        return consultas;
    }

    public void finalizarConsulta(Consulta consulta,Usuario usuario) {
        consulta.setFinalizadaConsulta(true);
        usuario.setDisponibilidadeUsuario(true);
        usuarioService.salvarUsuario(usuario);

        consultaRepository.save(consulta);
    }

    public Consulta encaminharConsulta(Long crmMedico) {
        List<Consulta> consultasOrdenadas = listarConsultasOrdenadasPorScore();
        Consulta consultaMaisUrgente = consultasOrdenadas.stream()
                .filter(c -> !c.getFinalizadaConsulta())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma consulta urgente encontrada"));
        consultaMaisUrgente.setCrmMedicoConsulta(crmMedico);
        return consultaRepository.save(consultaMaisUrgente);
    }

}
