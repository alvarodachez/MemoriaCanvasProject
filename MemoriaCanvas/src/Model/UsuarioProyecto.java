package Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(UsuarioProyectoId.class)
public class UsuarioProyecto {

	@Id
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario idUsuario;
	@Id
	@ManyToOne
	@JoinColumn(name="idProyecto")
	private Proyecto idProyecto;
	
	public UsuarioProyecto () {
		
	}
	
	public UsuarioProyecto(Usuario idUsuario, Proyecto idProyecto) {
		this.idProyecto =idProyecto;
		this.idUsuario = idUsuario;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Proyecto getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Proyecto idProyecto) {
		this.idProyecto = idProyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProyecto == null) ? 0 : idProyecto.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		UsuarioProyecto other = (UsuarioProyecto) obj;
		if (idProyecto == null) {
			if (other.idProyecto != null)
				return false;
		} else if (!idProyecto.equals(other.idProyecto))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
	
	
}
