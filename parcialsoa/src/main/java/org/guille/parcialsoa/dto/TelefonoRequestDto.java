package org.guille.parcialsoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TelefonoRequestDto {

	private Long idTelefono;
	private String numero;
	private Long idUsuario;
	private Double monto;
	
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

	@JsonProperty(value="id_usuario")
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
}
