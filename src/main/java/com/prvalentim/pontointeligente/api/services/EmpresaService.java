package com.prvalentim.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prvalentim.pontointeligente.api.entities.Empresa;
import com.prvalentim.pontointeligente.api.repositories.EmpresaRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa {}", empresa);
		return empresaRepository.save(empresa);
	}
}
