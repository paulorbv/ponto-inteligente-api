package com.prvalentim.pontointeligente.api.utils;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {

	private static final String senha = "123456";
	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Test
	public void testSenhaNulo() throws Exception {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}

	@Test
	public void testGerarHashSenha() throws Exception {
		String hash = PasswordUtils.gerarBCrypt(senha);

		assertTrue(bCryptPasswordEncoder.matches(senha, hash));
	}

}
