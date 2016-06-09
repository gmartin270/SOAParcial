package org.guille.parcialsoa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TELEFONO")
public class Telefono extends ObjetoGenerico {
	
	@Column(name="NUMERO", length=30, nullable=false)
	private String numero;
	
	@Column(name="SALDO", nullable=false)
	private Double saldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID", nullable = false)
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
