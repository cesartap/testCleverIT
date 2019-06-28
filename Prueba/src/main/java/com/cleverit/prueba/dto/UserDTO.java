package com.cleverit.prueba.dto;

import lombok.Data;

/**
 * CLase para mapeo de Objeto de respuesta de servicio Rest proporcionado para
 * prueba
 * 
 * @author Cesar
 *
 */
@Data
public class UserDTO {
	private int id;
	private String email;
	private String lastName;
	private String name;
	private String password;
	private String Hola;
}
