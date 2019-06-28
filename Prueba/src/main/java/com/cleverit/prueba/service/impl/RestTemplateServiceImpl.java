package com.cleverit.prueba.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cleverit.prueba.dao.ILicensePlateDAO;
import com.cleverit.prueba.dto.UserDTO;
import com.cleverit.prueba.entity.LicensePlate;
import com.cleverit.prueba.service.IRestTemplateService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de realizar la logica para usuarios y para licencias Se
 * utiliza una BD en MySQL con el fin de hacer mas rapido el desarrollo y
 * utilizando Spring DATA con creacion de tablas automatico
 * 
 * @author Cesar
 *
 */
@Service
@Slf4j
@Transactional
public class RestTemplateServiceImpl implements IRestTemplateService {

	@Autowired
	ILicensePlateDAO licenseDAO;

	/**
	 * Obtencion y guardado de licencias
	 */
	@Override
	public Boolean fetchDataLicensePlate() throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object[]> licenssePlate = restTemplate
				.getForEntity("http://jsonverserverclever.azurewebsites.net/LicensePlate", Object[].class);

		if (null != licenssePlate.getBody()) {
			log.info(licenssePlate.toString());

			for (Object iterableElement : licenssePlate.getBody()) {
				log.info(iterableElement.toString());
				LicensePlate licensePlate = new LicensePlate();

				@SuppressWarnings("rawtypes")
				java.util.LinkedHashMap hasmap = ((java.util.LinkedHashMap) iterableElement);
				licensePlate.setId(new Integer(hasmap.get("id").toString()));
				licensePlate.setAlias(hasmap.get("alias") == null ? null : hasmap.get("alias").toString());
				licensePlate.setIdUser(hasmap.get("idUser") == null ? null : hasmap.get("idUser").toString());
				licensePlate.setLicensePlate(
						hasmap.get("licensePlate") == null ? null : hasmap.get("licensePlate").toString());
				licenseDAO.save(licensePlate);

			}

		} else {
			throw new Exception(
					"[RestTemplateServiceImpl] [fetchDataLicensePlate] No se pudo guardar la informacion u obtener la informacion del servicio");
		}
		return true;
	}

	/**
	 * Creacion de usuario
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public UserDTO createUser(UserDTO user) {
		log.info("[RestTemplateServiceImpl] [createUser] Inicio Metodo");

		// Se hace un llamado nuevo al getAllUsers para obtener el ultimo ID

		List<UserDTO> resultadoALlUsers = this.getAllUsers();
		int i = 0;
		if (resultadoALlUsers != null && resultadoALlUsers.size() > 0) {

			Object userObj = resultadoALlUsers.get(0);
			i = Integer.valueOf(((java.util.LinkedHashMap) userObj).get("id").toString());
			i++;
		}
		user.setId(i);

		// Se guarda el nuevo elemento
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject("http://jsonverserverclever.azurewebsites.net/User/", user, UserDTO.class);

		log.info("[RestTemplateServiceImpl] [createUser] Fin Metodo");
		return user;
	}

	/**
	 * Obtencion de todos los usuarios
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> getAllUsers() {
		log.info("[RestTemplateServiceImpl] [getAllUsers] Inicio Metodo");

		RestTemplate restTemplate = new RestTemplate();
		Object user = restTemplate
				.getForObject("http://jsonverserverclever.azurewebsites.net/User?_sort=id&_order=desc", Object.class);

		log.info("[RestTemplateServiceImpl] [getAllUsers] Fin Metodo");
		return (List<UserDTO>) user;
	}

	/**
	 * Obtencion de usuario en especifico
	 */
	@Override
	public UserDTO getUserForId(int id) {
		log.info("[RestTemplateServiceImpl] [getUserForId] Inicio Metodo");

		RestTemplate restTemplate = new RestTemplate();
		UserDTO user = restTemplate.getForObject("http://jsonverserverclever.azurewebsites.net/User/" + id,
				UserDTO.class);

		log.info("[RestTemplateServiceImpl] [getUserForId] Fin Metodo");
		return user;
	}

	/**
	 * Actualizacion de usuario en especifico
	 */
	@Override
	public UserDTO updateUser(UserDTO user) {
		log.info("[RestTemplateServiceImpl] [updateUser] Inicio Metodo");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put("http://jsonverserverclever.azurewebsites.net/User/" + user.getId(), user);

		log.info("[RestTemplateServiceImpl] [updateUser] Fin Metodo");
		return user;
	}

	/**
	 * Eliminacion de usuario en especifo
	 */
	@Override
	public Boolean deleteUser(int id) {
		log.info("[RestTemplateServiceImpl] [deleteUser] Inicio Metodo");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://jsonverserverclever.azurewebsites.net/User/" + id);

		log.info("[RestTemplateServiceImpl] [deleteUser] Fin Metodo");
		return true;
	}

}
