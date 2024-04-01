package com.unnoba.socialclub.dto;

import com.unnoba.socialclub.entities.Rol;

public class UsuarioRegistroDTO {

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String rol_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getRol_id() {
		return rol_id;
	}

	public void setRol_id(String rol_id) {
		this.rol_id = rol_id;
	}
	

	public UsuarioRegistroDTO(String nombre, String apellido, String email, String password, String rol_id) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.rol_id = rol_id;
	}

	public UsuarioRegistroDTO() {

	}
}
