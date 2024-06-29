package br.com.med.voll.api.dto.medico;

import br.com.med.voll.api.dto.endereco.DadosEnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEnderecoDTO endereco) {
}
