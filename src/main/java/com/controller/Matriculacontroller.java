package com.edu.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edu.pe.config.UtilsProperties;

import com.edu.pe.message.MatriculaMessagePublish;
import com.edu.pe.model.MatriculaModel;
import com.edu.pe.service.MatriculaService;

@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {
	private Logger logger = LoggerFactory.getLogger(MatriculaController.class);

	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private UtilsProperties prop;
	
    @Autowired
    MatriculaMessagePublish messageEvent;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<MatriculaModel> lista = matriculaService.findAll();

			List<MatriculaDTO> listaDTO = lista.stream().map(x -> {
				ModelMapper m = new ModelMapper();
				return m.map(x, MatriculaDTO.class);
			}).collect(Collectors.toList());
			
			salida.put("data", listaDTO);
			
			logger.info("GET[Response] {}", listaDTO);
			return new ResponseEntity<>(salida, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			salida.put("msg", prop.MSG_ERROR_RECUPERAR);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(salida);
		}
	}
	