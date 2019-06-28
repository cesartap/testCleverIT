package com.cleverit.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cleverit.prueba.dto.UserDTO;
import com.cleverit.prueba.dto.UserResponse;
import com.cleverit.prueba.service.IRestTemplateService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de exponer las operaciones CRUD para el usuario mediante
 * usercontroller
 * 
 * @author Cesar
 *
 */
@RestController
@RequestMapping("/usercontroller")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

	@Autowired
	private IRestTemplateService service;

	/**
	 * Creacion de un usuario
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/user")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO createUser(@RequestBody UserDTO user) {
		log.info("[UserController] [createUser] Inicio Metodo");
		UserDTO userResponse = null;
		try {
			userResponse = service.createUser(user);
		} catch (Exception e) {
			log.error("[UserController] [createUser] Error en Metodo", e);
		}
		log.info("[UserController] [createUser] Fin Metodo");
		return userResponse;
	}

	/**
	 * Obtencion de todos los usuarios
	 * 
	 * @return
	 */
	@GetMapping(value = "/users")
	public @ResponseBody UserResponse getAllUsers() {
		log.info("[UserController] [getAllUsers] Inicio Metodo");
		UserResponse userResponse = new UserResponse();

		try {
			List<UserDTO> userList = service.getAllUsers();
			userResponse.setUserList(userList);
		} catch (Exception e) {
			log.error("[UserController] [getAllUsers] Error en Metodo", e);
		}

		log.info("[UserController] [getAllUsers] Fin Metodo");
		return userResponse;
	}

	/**
	 * Obtencion de un usuario especifico
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/user/{id}")
	public @ResponseBody UserDTO getUserForId(@PathVariable("id") int id) {
		log.info("[UserController] [getUserForId] Inicio Metodo");
		UserDTO userResponse = null;
		try {
			userResponse = service.getUserForId(id);
		} catch (Exception e) {
			log.error("[UserController] [getUserForId] Error en Metodo", e);
		}
		log.info("[UserController] [getUserForId] Fin Metodo");
		return userResponse;
	}

	/**
	 * Actualizacion de un usuario
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping(value = "/user")
	public @ResponseBody UserDTO updateUser(@RequestBody UserDTO user) {
		log.info("[UserController] [updateUser] Inicio Metodo");
		UserDTO userResponse = null;
		try {
			userResponse = service.updateUser(user);
		} catch (Exception e) {
			log.error("[UserController] [updateUser] Error en Metodo", e);
		}
		log.info("[UserController] [updateUser] Fin Metodo");
		return userResponse;
	}

	/**
	 * Eliminacion de un usuario especifico
	 * 
	 * @param id
	 * @return True, eliminado correctamente
	 */
	@DeleteMapping(value = "/user/{id}")
	public @ResponseBody Boolean deleteUser(@PathVariable("id") int id) {
		log.info("[UserController] [deleteUser] Inicio Metodo");
		Boolean userResponse = false;
		try {
			userResponse = service.deleteUser(id);
		} catch (Exception e) {
			log.error("[UserController] [deleteUser] Error en Metodo", e);
		}
		log.info("[UserController] [deleteUser] Fin Metodo");
		return userResponse;
	}

}
