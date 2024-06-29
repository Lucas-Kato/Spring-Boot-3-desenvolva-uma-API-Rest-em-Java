package br.com.med.voll.api.dto.paciente;

import br.com.med.voll.api.dto.endereco.DadosEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPacienteDTO(@NotNull
                                        Long id,
                                        String nome,
                                        String telefone,
                                        @Valid
                                        DadosEnderecoDTO endereco) {
}
