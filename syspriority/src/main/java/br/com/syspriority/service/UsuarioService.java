package br.com.syspriority.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.syspriority.model.Usuario;
import br.com.syspriority.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario) {
        boolean isNovo = usuario.getIdUsuario() == null;

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenhaUsuario());
        usuario.setSenhaUsuario(senhaCriptografada);

        if (!isNovo && usuarioRepository.existsById(usuario.getIdUsuario())) {
            Usuario existente = usuarioRepository.findById(usuario.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Erro ao achar usuário para atualização"));

            existente.setNomeUsuario(usuario.getNomeUsuario());
            existente.setCrmUsuario(usuario.getCrmUsuario());
            existente.setTipoUsuario(usuario.getTipoUsuario());
            existente.setSenhaUsuario(usuario.getSenhaUsuario());

            return usuarioRepository.save(existente);

        } else {
            return usuarioRepository.save(usuario);
        }
    }
    
    public void excluirUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado para exclusão");
        }
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarMedicos(String tipoUsuario) {
        return usuarioRepository.findByTipoUsuario(tipoUsuario);
    }

    public List<Usuario> listarMedicosDisponiveis(String tipoUsuario) {
        return usuarioRepository.findByDisponibilidadeUsuarioTrueAndTipoUsuario(tipoUsuario);
    }

}
