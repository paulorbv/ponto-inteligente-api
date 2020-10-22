package com.prvalentim.pontointeligente.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class PasswordUtils {

	public static String gerarBCrypt(String password) {

		if (password == null) {
			return null;
		}
		
		log.info("Gerando Hash com BCrypt");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(password);
	}
	

}
