package Model;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Conexion {

	private static StandardServiceRegistry sr;
	private static SessionFactory sf;
	private static Session ses;
	private static Conexion SESSION = null;

	private Conexion() {

	}

	private static void createSession() {
		if (SESSION == null) {
			SESSION = new Conexion();
			sr = new StandardServiceRegistryBuilder().configure().build();
			sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
			ses = sf.openSession();
		}
	}

	public static Conexion getSession() {
		if (SESSION == null) {
			createSession();
		}
		return SESSION;
	}

	public void closeSession() {
		ses.close();
		sf.close();
	}

	public boolean insertarTarea(int idTarea, String nombre, String estado, int duracion, int idUser) {

		Tarea t1 = new Tarea(idTarea, nombre, estado, duracion, idUser);

		ses.getTransaction().begin();
		ses.save(t1);
		ses.getTransaction().commit();

		return true;
	}

	public boolean insertarTarea(String nombre, String estado, int duracion, int idUser) {

		int idTarea = 0;
		Query query = ses.createQuery("SELECT MAX(idTarea) FROM Tarea");
		idTarea = ((int) query.getSingleResult()) + 1;
		Tarea t1 = new Tarea(idTarea, nombre, estado, duracion, idUser);

		ses.getTransaction().begin();
		ses.save(t1);
		ses.getTransaction().commit();

		return true;
	}

	public boolean actualizarEstado(int idTarea, String estado) {
		Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
		t1.setEstado(estado);
		ses.getTransaction().begin();
		ses.update(t1);
		ses.getTransaction().commit();

		return true;
	}
	
	public boolean actualizarEstado(int idTarea, String estado,int idUsuario)throws Exception {
		Query query = ses.createQuery("SELECT idUser FROM Tarea WHERE idTarea = "+idTarea);
		int idUs = (int)query.getSingleResult();
		
		if(idUs != idUsuario) {
			throw new Exception("ID de tarea incorrecto");
		}
			
		Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
		t1.setEstado(estado);
		ses.getTransaction().begin();
		ses.update(t1);
		ses.getTransaction().commit();

		return true;
	}

	public boolean borrarTarea(int idTarea) {
		Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
		ses.getTransaction().begin();
		ses.delete(t1);
		ses.getTransaction().commit();

		return true;
	}
	
	public boolean borrarTarea(int idTarea,int idUsuario)throws Exception {
		
		Query query = ses.createQuery("SELECT idUser FROM Tarea WHERE idTarea = "+idTarea);
		int idUs = (int)query.getSingleResult();
		
		if(idUs != idUsuario) {
			throw new Exception("ID de tarea incorrecto");
		}
		Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
		ses.getTransaction().begin();
		ses.delete(t1);
		ses.getTransaction().commit();

		return true;
	}

	public boolean insertarUsuario(int idUsuario, String apodo, String contraseña) {

		Usuario u1 = new Usuario(idUsuario, apodo, contraseña);

		ses.getTransaction().begin();
		ses.save(u1);
		ses.getTransaction().commit();

		return true;
	}

	public boolean insertarUsuario(String apodo, String contraseña) {
		int idUsuario = 0;
		Query query = ses.createQuery("SELECT MAX(idUsuario) FROM Usuario");
		idUsuario = ((int) query.getSingleResult()) + 1;
		Usuario u1 = new Usuario(idUsuario, apodo, contraseña);

		ses.getTransaction().begin();
		ses.save(u1);
		ses.getTransaction().commit();

		return true;
	}

	public int devolverIdIniciarSesion(String apodo, String contraseña) {
		Query query = ses.createQuery("SELECT idUsuario FROM Usuario WHERE UPPER(apodo) LIKE \'" + apodo.toUpperCase()
				+ "\' AND UPPER(contraseña) LIKE \'" + contraseña.toUpperCase() + "\'");
		// Query query = ses.createQuery("SELECT idUsuario FROM Usuario WHERE
		// UPPER(apodo) LIKE :ap AND UPPER(contraseña) LIKE :cs");
		//// query.setParameter("cs", contraseña);

		int idUsuario = (int) query.getSingleResult();
		return idUsuario;
	}

	public ArrayList<Tarea> devolverMisTareas(int idUsuario) {

		Query query = ses.createQuery("SELECT t FROM Tarea t WHERE idUser = " + idUsuario+" ORDER BY idTarea ASC");
		ArrayList<Tarea> misTareas = new ArrayList<Tarea>();
		misTareas = (ArrayList<Tarea>) query.getResultList();

		return misTareas;
	}
}
