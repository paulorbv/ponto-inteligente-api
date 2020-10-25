package com.prvalentim.pontointeligente.api.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaDto {

	private Long id;

	@NotNull(message = "Razão social não pode ser vazio.")
	@Size(min = 5, max = 200, message = "Razão social deve conter entre 5 e 200 caracteres.")
	private String razaoSocial;

	@NotNull(message = "CNPJ não pode ser vazio.")
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;
	
}
