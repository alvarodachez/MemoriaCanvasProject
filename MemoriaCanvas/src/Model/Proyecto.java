package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proyecto {

	@Id
	private int idProyecto;
	private String nombre;
	
	@OneToMany(mappedBy = "idProyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UsuarioProyecto> usuarios;
	
	@OneToMany(mappedBy = "idProyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Objetivo> idObjetivo;
	
	public Proyecto() {
		usuarios = new ArrayList<UsuarioProyecto>();
		idObjetivo = new ArrayList<Objetivo>();
	}
	
	public Proyecto (int idProyecto, String nombre) {
		this.idProyecto = idProyecto;
		this.nombre = nombre;
		usuarios = new ArrayList<UsuarioProyecto>();
		idObjetivo = new ArrayList<Objetivo>();
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void añadirObjetivo(Objetivo o) {
		this.idObjetivo.add(o);
	}

	public ArrayList<UsuarioProyecto>getUsuarios(){
		ArrayList<UsuarioProyecto> aux = new ArrayList<UsuarioProyecto>();
		
		aux.addAll(this.usuarios);
		return aux;
	}
	public void borrarUsuarioProyecto(int idUsuario) {
		for(UsuarioProyecto u : this.usuarios) {
			if(u.getIdUsuario().getIdUsuario() == idUsuario) {
				this.usuarios.remove(u);
			}
		}
	}

	public void setUsuarios(List<UsuarioProyecto> usuarios) {
		this.usuarios = usuarios;
	}
	public void modificarPrioridad(int idObjetivo, String prioridad) {
		for(Objetivo o : this.idObjetivo) {
			if(o.getIdObjetivo() == idObjetivo) {
//				System.out.println(t.getEstado());
				o.setPrioridad(prioridad);
//				System.out.println(t.getEstado());
			}
		}
	}
	
	public List<Objetivo> getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(List<Objetivo> idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public void borrarObjetivo(Objetivo o) {
		idObjetivo.remove(o);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProyecto;
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
		Proyecto other = (Proyecto) obj;
		if (idProyecto != other.idProyecto)
			return false;
		return true;
	}
	
	

	
}
