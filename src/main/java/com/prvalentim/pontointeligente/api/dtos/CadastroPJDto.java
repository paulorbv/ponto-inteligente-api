package com.prvalentim.pontointeligente.api.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroPJDto {
	
	private Long id;
	
	@NotNull(message = "Nome não pode ser vazio.")
	@Size(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	@NotNull(message = "Email não pode ser vazio.")
	@Size(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message="Email inválido.")
	private String email;
	
	@NotNull(message = "Senha não pode ser vazia.")
	private String senha;
	
	@NotNull(message = "CPF não pode ser vazio.")
	@CPF(message="CPF inválido")
	private String cpf;
	
	@NotNull(message = "Razão social não pode ser vazio.")
	@Size(min = 5, max = 200, message = "Razão social deve conter entre 5 e 200 caracteres.")
	private String razaoSocial;
	
	@NotNull(message = "CNPJ não pode ser vazio.")
	@CNPJ(message="CNPJ inválido.")
	private String cnpj;

	@Override
	public String toString() {
		return "CadastroPJDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}

}
