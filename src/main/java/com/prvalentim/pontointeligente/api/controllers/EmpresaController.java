package com.prvalentim.pontointeligente.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prvalentim.pontointeligente.api.dtos.EmpresaDto;
import com.prvalentim.pontointeligente.api.entities.Empresa;
import com.prvalentim.pontointeligente.api.response.Response;
import com.prvalentim.pontointeligente.api.services.EmpresaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
@Slf4j
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	public EmpresaController() {
	}

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		log.info("Buscando empresa por CNPJ: {}", cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);

		if (!empresa.isPresent()) {
			log.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Popula um DTO com os dados de uma empresa.
	 * 
	 * @param empresa
	 * @return EmpresaDto
	 */
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		return EmpresaDto.builder().id(empresa.getId())
				.cnpj(empresa.getCnpj())
				.razaoSocial(empresa.getRazaoSocial()).build();

	}

}
