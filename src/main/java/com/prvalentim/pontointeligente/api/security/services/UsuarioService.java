package com.prvalentim.pontointeligente.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prvalentim.pontointeligente.api.security.entities.Usuario;
import com.prvalentim.pontointeligente.api.security.repositories.UsuarioRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("Buscando um usuario por email {}", email);
		return Optional.ofNullable(usuarioRepository.findByEmail(email));
	}

}
