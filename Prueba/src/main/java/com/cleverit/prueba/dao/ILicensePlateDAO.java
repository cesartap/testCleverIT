package com.cleverit.prueba.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cleverit.prueba.entity.LicensePlate;

/**
 * Clase que expone mediante Spring Data, CRUD automaticos hacia la tabla
 * license (Extra para motivos de prueba)
 * 
 * @author Cesar
 *
 */
@RepositoryRestResource(path = "license")
public interface ILicensePlateDAO extends JpaRepository<LicensePlate, String> {

}
