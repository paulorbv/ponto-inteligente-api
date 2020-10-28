package com.prvalentim.pontointeligente.api.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.prvalentim.pontointeligente.api.entities.Funcionario;
import com.prvalentim.pontointeligente.api.services.FuncionarioService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FuncionarioControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FuncionarioService funcionarioService;

	private static final String BUSCAR_Funcionario_CPF_URL = "/api/funcionarios/cpf/";

	private static final String CPF = "00935579575";

	private static final Long ID = 1L;

	private static final String NOME = "Paulo";

	private static final BigDecimal VALOR_HORA = BigDecimal.valueOf(10);

	@Test
	@WithMockUser
	public void testBuscarFuncionarioCnpjInvalido() throws Exception {
		BDDMockito.given(this.funcionarioService.buscarPorCpf(Mockito.anyString())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_Funcionario_CPF_URL + CPF).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Funcionario não encontrado para o CPF " + CPF));
	}

	@Test
	@WithMockUser
	public void testBuscarFuncionarioCpfValido() throws Exception {
		BDDMockito.given(this.funcionarioService.buscarPorCpf(Mockito.anyString()))
				.willReturn(Optional.of(this.obterDadosFuncionario()));

		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_Funcionario_CPF_URL + CPF).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.nome", equalTo(NOME))).andExpect(jsonPath("$.errors").isEmpty());
	}

	private Funcionario obterDadosFuncionario() {

		return Funcionario.builder().id(ID).nome(NOME).valorHora(VALOR_HORA).build();

	}

}
