package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.paciente.DadosAtualizarPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import br.com.med.voll.api.model.paciente.Paciente;
import br.com.med.voll.api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping("cadastrar")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados){
       pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping("listar")
    public Page<DadosListagemPacienteDTO> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return pacienteRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemPacienteDTO::new);
    }

    @PutMapping("autualiar")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarPacienteDTO dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());

        paciente.atualizarInformacaoPaciente(dados);
    }

    @DeleteMapping("excluir/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);

        paciente.desativarPaciente();
    }
}
