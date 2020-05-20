package Model;

import java.io.Serializable;

public class UsuarioProyectoId implements Serializable{

	private int idUsuario;
	private int idProyecto;
	
	public UsuarioProyectoId() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProyecto;
		result = prime * result + idUsuario;
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
		UsuarioProyectoId other = (UsuarioProyectoId) obj;
		if (idProyecto != other.idProyecto)
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}
	
	
	
}
