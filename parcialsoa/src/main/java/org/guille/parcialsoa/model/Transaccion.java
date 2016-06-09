package org.guille.parcialsoa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRANSACCION")
public class Transaccion extends ObjetoGenerico{
	
	@Column(name="FECHATRANSACCION", nullable=false)
	private Date fechaTransaccion;
	
	@Column(name="MONTO", nullable=false)
	private Double monto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TELEFONO_ID", nullable = false)
	private Telefono telefono;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="MOVIMIENTO")
	private Movimiento movimiento;

	
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	

}
