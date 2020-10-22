package com.prvalentim.pontointeligente.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.prvalentim.pontointeligente.api.entities.Funcionario;
import com.prvalentim.pontointeligente.api.repositories.FuncionarioRepository;

@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {

	public static final String CPF = "00935578875";
	public static final String EMAIL = "paulorteste@gmail.com";
	private static Long id = 1L;

	@Mock
	private FuncionarioRepository funcionarioRepository;

	@InjectMocks
	private FuncionarioService funcionarioService;

	@BeforeEach
	public void setup() {
		
		BDDMockito.given(funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(Funcionario.builder().build());
		BDDMockito.given(funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(Funcionario.builder().build());
		BDDMockito.given(funcionarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(Funcionario.builder().build()));
		BDDMockito.given(funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(Funcionario.builder().build());
		
	}

	@Test
	public void testBuscarEmpresaPorCPF() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorCpf(CPF);
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testBuscarEmpresaPorEmail() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorEmail(EMAIL);
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testBuscarPorId() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
		assertTrue(funcionario.isPresent());
	}

	@Test
	public void testPersistEmpresa() {
		Funcionario funcionario = funcionarioService.persistir(Funcionario.builder().build());
		assertNotNull(funcionario);
	}

	
}
