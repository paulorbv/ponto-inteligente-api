package com.prvalentim.pontointeligente.api.dtos;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuncionarioDto {

	private Long id;

	@NotNull(message = "Nome não pode ser vazio.")
	@Size(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;

	@NotNull(message = "Email não pode ser vazio.")
	@Size(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	private String email;

	@Builder.Default
	private Optional<String> senha = Optional.empty();
	
	@Builder.Default
	private Optional<String> valorHora = Optional.empty();
	
	@Builder.Default
	private Optional<String> qtdHorasTrabalhoDia = Optional.empty();
	
	@Builder.Default
	private Optional<String> qtdHorasAlmoco = Optional.empty();

	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", valorHora="
				+ valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHorasAlmoco=" + qtdHorasAlmoco
				+ "]";
	}

}
