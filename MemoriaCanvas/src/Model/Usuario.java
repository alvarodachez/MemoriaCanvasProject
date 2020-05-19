package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	private int idUsuario;
	private String apodo;
	private String contraseña;
	@OneToMany(mappedBy ="idUsuario",cascade = CascadeType.ALL, orphanRemoval = true ) 
	private List<Tarea> tareas;
	
	public Usuario() {
		tareas = new ArrayList<Tarea>();
	}
	
	public Usuario(int idUsuario, String apodo, String contraseña ) {
		this.idUsuario = idUsuario;
		this.apodo = apodo;
		this.contraseña = contraseña;
		this.tareas = new ArrayList<Tarea>();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public void añadirTarea(Tarea t) {
		tareas.add(t);
	}
	
	public void borrarTarea(Tarea t) {
		tareas.remove(t);
	}
	
	public void modificarEstado(int idTarea, String estado) {
		for(Tarea t : this.tareas) {
			if(t.getIdTarea() == idTarea) {
//				System.out.println(t.getEstado());
				t.setEstado(estado);
//				System.out.println(t.getEstado());
			}
		}
	}
	
	public ArrayList<Tarea> getTareas(){
		ArrayList<Tarea> aux = new ArrayList<Tarea>();
		
		aux.addAll(this.tareas);
		aux.sort(new ComparatorId());
		return aux;
	}
	
	public ArrayList<Tarea> getTareas(String estado){
		ArrayList<Tarea> aux = new ArrayList<Tarea>();
		
		for(Tarea t : this.tareas) {
			if(t.getEstado().compareTo(estado) == 0) {
				aux.add(t);
			}
		}
		aux.sort(new ComparatorId());
		return aux;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", apodo=" + apodo + ", contraseña=" + contraseña + "]";
	}
	
	
	
	}
	
