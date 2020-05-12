package Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tarea {

	@Id
	private int idTarea;
	private String nombre;
	private String estado;
	private int duracion;
	private int idUser; //fk de una futura tabla usuarios
	
	public Tarea() {
		
	}
	
	public Tarea(int idTarea, String nombre, String estado, int duracion, int idUser) {
		this.idTarea = idTarea;
		this.nombre = nombre;
		this.estado = estado;
		this.duracion = duracion;
		this.idUser = idUser;
	}

	public int getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTarea;
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
		Tarea other = (Tarea) obj;
		if (idTarea != other.idTarea)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tarea [id=" + idTarea + ", nombre=" + nombre + ", estado=" + estado + ", duracion=" + duracion + ", idUser="
				+ idUser + "]";
	}
	
	
}
