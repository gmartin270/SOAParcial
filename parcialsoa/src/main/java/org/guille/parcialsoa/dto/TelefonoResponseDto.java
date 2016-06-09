package org.guille.parcialsoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TelefonoResponseDto {

	private Long idTelefono;
	private String numero;
	private Double saldo;
	private UsuarioDto usuario;
	
	@JsonProperty(value="id_telefono")
	public Long getIdTelefono() {
		return idTelefono;
	}
	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public UsuarioDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
}
