package com.cleverit.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cleverit.prueba.service.IRestTemplateService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase Controller que se encarga de exponer el servicio licencecontroller
 * 
 * @author Cesar
 *
 */
@RestController
@RequestMapping("/licencecontroller")
@Slf4j
public class LicenceController {

	@Autowired
	private IRestTemplateService service;

	/**
	 * Metodo que se encarga de obtener las licencias y de guardarlas en la BD
	 * 
	 * @return Boolean True si fue correctamente guardado, false si no lo fue
	 * @throws Exception
	 */
	@GetMapping(value = "/fetchlicenseplate")
	@ResponseStatus(HttpStatus.OK)
	public Boolean fetchLicensePlate() throws Exception {

		log.debug("[LicenceController][fetchLicensePlate] Inicio de metodo.");
		Boolean resp = false;

		try {
			resp = service.fetchDataLicensePlate();
		} catch (Exception e) {
			log.error("[LicenceController][fetchLicensePlate]", e);
			throw new Exception(e.getMessage(), e);
		}

		log.debug("[LicenceController][fetchLicensePlate] fin de metodo.");
		return resp;
	}
}
