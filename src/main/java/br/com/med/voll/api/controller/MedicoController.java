package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.medico.DadosAtualizarMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import br.com.med.voll.api.dto.medico.DadosListagemMedicoDTO;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("cadastrar")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados){
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping("listar")
    public Page<DadosListagemMedicoDTO> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedicoDTO::new);
    }

    @PutMapping("atualizar")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarMedicoDTO dados){
        var medico = medicoRepository.getReferenceById(dados.id());

        medico.atualizarInformacaoMedico(dados);
    }

    @DeleteMapping("excluir/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);

        medico.desativarMedico();
    }
}
