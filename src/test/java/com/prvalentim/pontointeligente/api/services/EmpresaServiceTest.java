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

import com.prvalentim.pontointeligente.api.entities.Empresa;
import com.prvalentim.pontointeligente.api.repositories.EmpresaRepository;

@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {

	public static final String CNPJ = "02974217000128";

	@Mock
	private EmpresaRepository empresaRepository;

	@InjectMocks
	private EmpresaService empresaService;

	@BeforeEach
	public void setup() {
//		when(empresaRepository.findByCnpj(Mockito.anyString())).thenReturn(Empresa.builder().build());
//		when(empresaRepository.save(Mockito.any(Empresa.class))).thenReturn(Empresa.builder().build());
		
		BDDMockito.given(empresaRepository.findByCnpj(Mockito.anyString())).willReturn(Empresa.builder().build());
		BDDMockito.given(empresaRepository.save(Mockito.any(Empresa.class))).willReturn(Empresa.builder().build());
	}

	@Test
	public void testBuscarEmpresaPorCNPJ() {
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(CNPJ);
		assertTrue(empresa.isPresent());
	}

	@Test
	public void testPersistEmpresa() {
		Empresa empresa = empresaService.persistir(Empresa.builder().build());
		assertNotNull(empresa);
	}

}
