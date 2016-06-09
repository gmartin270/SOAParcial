package org.guille.parcialsoa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario extends ObjetoGenerico {
	
	@Column(name="NOMBRE", length=300, nullable=false)
	private String nombre;
	
	@Column(name="APELLIDO", length=300, nullable=false)
	private String apellido;
	
	@Column(name="EMAIL", length=500, nullable=false)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<Telefono> telefonos = new HashSet<Telefono>();

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


	public Set<Telefono> getTelefonos() {
		return telefonos;
	}


	public void setTelefonos(Set<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
	
	

}
