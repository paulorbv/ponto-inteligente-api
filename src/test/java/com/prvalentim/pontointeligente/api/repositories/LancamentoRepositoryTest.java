package com.prvalentim.pontointeligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.prvalentim.pontointeligente.api.entities.Empresa;
import com.prvalentim.pontointeligente.api.entities.Funcionario;
import com.prvalentim.pontointeligente.api.entities.Lancamento;
import com.prvalentim.pontointeligente.api.enums.PerfilEnum;
import com.prvalentim.pontointeligente.api.enums.TipoEnum;
import com.prvalentim.pontointeligente.api.utils.PasswordUtils;

@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	private Long funcionarioId;

	@BeforeEach
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());

		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();

		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamentos.size());
	}

	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado() {
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId,
				PageRequest.of(0, 10));

		assertEquals(2, lancamentos.getTotalElements());
	}

	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
		
		return Lancamento.builder().data(new Date()).tipo(TipoEnum.INICIO_ALMOCO).funcionario(funcionario).build();
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		
		return Funcionario.builder()
				.nome("Fulano de Tal")
				.perfil(PerfilEnum.ROLE_USUARIO)
				.senha(PasswordUtils.gerarBCrypt("123456"))
				.cpf("24291173474")
				.email("email@email.com")
				.empresa(empresa).build();
	}

	private Empresa obterDadosEmpresa() {
		return Empresa.builder().razaoSocial("Empresa de Exemplo").cnpj("51463645000100").build();
	}


}
