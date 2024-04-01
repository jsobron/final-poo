package com.unnoba.socialclub.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.unnoba.socialclub.dto.UsuarioRegistroDTO;
import com.unnoba.socialclub.entities.Usuario;


public interface UsuarioServicio extends UserDetailsService{
	
	public Usuario guardar(UsuarioRegistroDTO registroDTO, String Rol);
	public Usuario findByEmail(String email);

	
}
