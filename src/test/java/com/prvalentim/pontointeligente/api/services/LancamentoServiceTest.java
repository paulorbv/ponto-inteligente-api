package com.prvalentim.pontointeligente.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.prvalentim.pontointeligente.api.entities.Lancamento;
import com.prvalentim.pontointeligente.api.repositories.LancamentoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {
	
	public static final String CPF = "00935578875";
	public static final String EMAIL = "paulorteste@gmail.com";
	private static Long id = 1L;

	@Mock
	private LancamentoRepository lancamentoRepository;

	@InjectMocks
	private LancamentoService lancamentoService;

	@BeforeEach
	public void setup() {
		
		BDDMockito.given(lancamentoRepository.findById(Mockito.anyLong())).willReturn(Optional.of(Lancamento.builder().build()));
		BDDMockito.given(lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(Lancamento.builder().build());
		BDDMockito.given(lancamentoRepository.findByFuncionarioId(Mockito.anyLong())).willReturn(new ArrayList<Lancamento>());
		BDDMockito.given(lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class))).willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		
	}

	@Test
	public void testBuscarPorId() {
		Optional<Lancamento> funcionario = lancamentoService.buscarPorId(id);
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testbuscarPorFuncionarioId() {
		Page<Lancamento> lancamento = lancamentoService.buscarPorFuncionarioId(id, PageRequest.of(0, 10));
		assertNotNull(lancamento);
	}

	@Test
	public void testPersistLancamento() {
		Lancamento lancamento = lancamentoService.persistir(Lancamento.builder().build());
		assertNotNull(lancamento);
	}

}
