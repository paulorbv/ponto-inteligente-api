package com.prvalentim.pontointeligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.prvalentim.pontointeligente.api.entities.Empresa;
import com.prvalentim.pontointeligente.api.entities.Funcionario;
import com.prvalentim.pontointeligente.api.enums.PerfilEnum;
import com.prvalentim.pontointeligente.api.utils.PasswordUtils;

@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String EMAIL = "email@email.com";
	private static final String CPF = "24291173474";

	@BeforeEach
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}

	@AfterEach
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);

		assertEquals(EMAIL, funcionario.getEmail());
	}

	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);

		assertEquals(CPF, funcionario.getCpf());
	}

	@Test
	public void testBuscarFuncionarioPorEmailECpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);

		assertNotNull(funcionario);
	}

	@Test
	public void testBuscarFuncionarioPorEmailOuCpfParaEmailInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, "email@invalido.com");

		assertNotNull(funcionario);
	}

	@Test
	public void testBuscarFuncionarioPorEmailECpfParaCpfInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("12345678901", EMAIL);

		assertNotNull(funcionario);
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		
		return Funcionario.builder()
				.nome("Fulano de Tal")
				.perfil(PerfilEnum.ROLE_USUARIO)
				.senha(PasswordUtils.gerarBCrypt("123456"))
				.cpf(CPF)
				.email(EMAIL)
				.empresa(empresa).build();
	}

	private Empresa obterDadosEmpresa() {
		return Empresa.builder().razaoSocial("Empresa de Exemplo").cnpj("51463645000100").build();
	}

}
