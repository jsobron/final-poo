package com.unnoba.socialclub.services;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unnoba.socialclub.dto.UsuarioRegistroDTO;
import com.unnoba.socialclub.entities.Rol;
import com.unnoba.socialclub.entities.Usuario;
import com.unnoba.socialclub.repositories.RolRepositorio;
import com.unnoba.socialclub.repositories.UsuarioRepositorio;


@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	
	private UsuarioRepositorio usuarioRepositorio;
	private RolRepositorio rolRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, RolRepositorio rolRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
		this.rolRepositorio = rolRepositorio;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO, String rol) {
		Rol rolUsuario = rolRepositorio.findByNombre(rol);
		
		
		Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(),registroDTO.getEmail(), passwordEncoder.encode(registroDTO.getPassword()), rolUsuario);
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Usuario usuario = usuarioRepositorio.findByEmail(username);
	    if (usuario == null) {
	        throw new UsernameNotFoundException("Usuario o contraseña inválidos");
	    }
	    
	    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol_id().getNombre());
	    return new User(usuario.getEmail(), usuario.getPassword(), Collections.singletonList(authority));
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepositorio.findByEmail(email);
	}

}