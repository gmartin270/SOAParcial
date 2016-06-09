package org.guille.parcialsoa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransaccionDto {

	private Long idTransaccion; 
	private String fechaTransaccion;
	private Double monto;
	private TelefonoResponseDto telefono;
	private String movimiento;
		
	@JsonProperty(value="id_transaccion")
	public Long getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	@JsonProperty(value="fecha_transaccion")
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public TelefonoResponseDto getTelefono() {
		return telefono;
	}
	public void setTelefono(TelefonoResponseDto telefono) {
		this.telefono = telefono;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
}
