package com.cleverit.prueba.dto;

/**
 * Clase para respuesta de CRUD
 */
import java.util.List;

import lombok.Data;

@Data
public class UserResponse {
private List<UserDTO> userList;
}
