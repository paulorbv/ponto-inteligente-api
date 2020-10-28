package com.prvalentim.pontointeligente.api.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthenticationDto {

	@Email(message = "Email inválido.")
	@NotNull(message = "Email não pode ser vazio.")
	private String email;

	@NotNull(message = "Senha não pode ser vazia.")
	private String senha;

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [email=" + email + ", senha=" + senha + "]";
	}
}
