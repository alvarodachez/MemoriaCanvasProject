package Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Objetivo {

	@Id
	private int idObjetivo;
	private String nombre;
	private String prioridad;
	@ManyToOne
	@JoinColumn(name="idProyecto")
	Proyecto idProyecto;
	public Objetivo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Objetivo(int idObjetivo, String nombre, Proyecto idProyect,String prioridad) {
		this.idObjetivo = idObjetivo;
		this.nombre = nombre;
		this.idProyecto = idProyect;
		this.prioridad = prioridad;
	}
	
	
	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public Proyecto getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Proyecto idProyecto) {
		this.idProyecto = idProyecto;
	}

	public int getIdObjetivo() {
		return idObjetivo;
	}
	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Proyecto getIdProyect() {
		return idProyecto;
	}
	public void setIdProyect(Proyecto idProyect) {
		this.idProyecto = idProyect;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idObjetivo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objetivo other = (Objetivo) obj;
		if (idObjetivo != other.idObjetivo)
			return false;
		return true;
	}
	
	
}
