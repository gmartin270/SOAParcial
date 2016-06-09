package org.guille.parcialsoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDto {

	private Long idUsuario;
	private String nombre;
	private String apellido;
	private String email;
	
	@JsonProperty(value="id_usuario")
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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
}
