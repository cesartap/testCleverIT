package com.cleverit.prueba.service;

import java.util.List;

import com.cleverit.prueba.dto.UserDTO;

public interface IRestTemplateService {

	/**
	 * Traer y guardar en BD las licencias
	 * 
	 * @return
	 * @throws Exception
	 */
	public Boolean fetchDataLicensePlate() throws Exception;

	/**
	 * Crear un usuario
	 * 
	 * @param user
	 * @return
	 */
	public UserDTO createUser(UserDTO user);

	/**
	 * Traer todos los usuarios
	 * 
	 * @return
	 */
	public List<UserDTO> getAllUsers();

	/**
	 * Traer un usuario especifico
	 */
	public UserDTO getUserForId(int id);

	/**
	 * Actualizar un usuario especifico
	 * 
	 * @param user
	 * @return
	 */
	public UserDTO updateUser(UserDTO user);

	/**
	 * Eliminar un usuario especifico
	 * 
	 * @param id
	 * @return
	 */
	public Boolean deleteUser(int id);
}
