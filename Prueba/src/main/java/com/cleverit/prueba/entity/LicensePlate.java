package com.cleverit.prueba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Clase encargada de mapear la informacion a la BD de manera automatica
 * 
 * @author Cesar
 *
 */
@Data
@Entity
public class LicensePlate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String alias;
	private String idUser;
	private String licensePlate;
}
