package com.prvalentim.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prvalentim.pontointeligente.api.entities.Funcionario;
import com.prvalentim.pontointeligente.api.repositories.FuncionarioRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Persistindo funcionário: {}", funcionario);
		return this.funcionarioRepository.save(funcionario);
	}
	
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando funcionário pelo CPF {}", cpf);
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}
	
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionário pelo email {}", email);
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}
	
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionário pelo IDl {}", id);
		return this.funcionarioRepository.findById(id);
	}
	
}
