package com.prvalentim.pontointeligente.api.dtos;


import java.util.Optional;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LancamentoDto {
	
	@Builder.Default
	private Optional<Long> id = Optional.empty();
	
	@NotNull(message = "Data não pode ser vazia.")
	private String data;
	
	@NotNull(message = "Tipo não pode ser vazio.")
	private String tipo;
	
	private String descricao;
	
	private String localizacao;
	
	@NotNull(message = "Funcionario Id não pode ser vazio.")
	private Long funcionarioId;



	@Override
	public String toString() {
		return "LancamentoDto [id=" + id + ", data=" + data + ", tipo=" + tipo + ", descricao=" + descricao
				+ ", localizacao=" + localizacao + ", funcionarioId=" + funcionarioId + "]";
	}
	
}
