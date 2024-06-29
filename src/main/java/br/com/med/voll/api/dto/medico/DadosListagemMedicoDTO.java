package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.model.medico.Medico;

public record DadosListagemMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico){
        this(   medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade());
    }
}
