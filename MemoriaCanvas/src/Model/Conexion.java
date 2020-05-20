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

	// ********CONEXION*********

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

	// **********INSERTAR TAREAS***************
	public boolean insertarTarea(String nombre, String estado, int duracion, int idUser) {

		int idTarea = 0;
		Query query = ses.createQuery("SELECT MAX(idTarea) FROM Tarea");
		idTarea = ((int) query.getSingleResult()) + 1;
		Usuario us = (Usuario) ses.get(Usuario.class, idUser);
		Tarea t1 = new Tarea(idTarea, nombre, estado, duracion, us);

		us.añadirTarea(t1);

		ses.getTransaction().begin();

		ses.saveOrUpdate(us);
		ses.getTransaction().commit();

		return true;
	}
	// ************ACTUALIZAR TAREAS***************

	public boolean actualizarEstado(int idTarea, String estado, int idUsuario) throws Exception {

		Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);

		Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);

		int idUs = t1.getIdUser();

		if (idUs != idUsuario) {
			throw new Exception("ID de tarea incorrecto");
		}

		us.modificarEstado(idTarea, estado);

		ses.beginTransaction();

		ses.update(us);

		ses.getTransaction().commit();

		return true;
	}

	// ***************BORRAR TAREAS**********************
	public boolean borrarTarea(int idTarea, int idUsuario) throws Exception {

		Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
		Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);
		int idUs = t1.getIdUser();

		if (idUs != idUsuario) {
			throw new Exception("ID de tarea incorrecto");
		}

		us.borrarTarea(t1);
		ses.getTransaction().begin();
		ses.update(us);
		ses.delete(t1);
		ses.getTransaction().commit();

		return true;
	}

	// ***************CREAR USARIOS**************************

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

		int idUsuario = (int) query.getSingleResult();
		return idUsuario;
	}

	// *********************DEVOLVER TAREAS********************

	// *****Devolver todas las tareas de un usuario******
	public ArrayList<Tarea> devolverMisTareas(int idUsuario) {

		Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);

		return us.getTareas();
	}

	// ******Devolver todas las tareas segun su estado*****

	public ArrayList<Tarea> devolverMisTareas(String status) {

		Query query = ses.createQuery(
				"SELECT t FROM Tarea t WHERE UPPER(estado) LIKE \'" + status.toUpperCase() + "\' ORDER BY idTarea ASC");
		ArrayList<Tarea> misTareas = new ArrayList<Tarea>();
		misTareas = (ArrayList<Tarea>) query.getResultList();

		return misTareas;
	}
	// *****Devolver tareas de un usuario segun su estado****

	public ArrayList<Tarea> devolverMisTareas(int idUsuario, String status) {
		Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);

		return us.getTareas(status.toUpperCase());
	}

	// *****Devolver todas las tareas******
	public ArrayList<Tarea> devolverTareas() {
		Query query = ses.createQuery("SELECT t FROM Tarea t ORDER BY idTarea ASC");
		ArrayList<Tarea> misTareas = new ArrayList<Tarea>();
		misTareas = (ArrayList<Tarea>) query.getResultList();

		return misTareas;
	}

	// *****************DEVOLVER USUARIOS******************************************
	public ArrayList<Usuario> devolverUsuarios() {
		Query query = ses.createQuery("SELECT u FROM Usuario u ORDER BY idUsuario ASC");
		ArrayList<Usuario> usus = new ArrayList<Usuario>();
		usus = (ArrayList<Usuario>) query.getResultList();
		return usus;
	}

	// *******Devolver usuario segun su apodo*****
	public ArrayList<Usuario> devolverUsuarios(String apodoUsuario) {
		Query query = ses.createQuery("SELECT u FROM Usuario u WHERE UPPER(apodo) LIKE \'" + apodoUsuario.toUpperCase()
				+ "\' ORDER BY idUsuario ASC");
		ArrayList<Usuario> usus = new ArrayList<Usuario>();
		usus = (ArrayList<Usuario>) query.getResultList();
		return usus;
	}

	// *******************BORRAR USUARIOS********************
	public boolean borrarUsuario(int idUsuario) {
		Usuario u1 = (Usuario) ses.get(Usuario.class, idUsuario);
		ses.getTransaction().begin();
		ses.delete(u1);
		ses.getTransaction().commit();

		return true;
	}

	// *******************INSERTAR PROYECTO********************

	public boolean insertarProyecto(String nombre) {

		int idProyecto = 0;
		Query query = ses.createQuery("SELECT MAX(idProyecto) FROM Proyecto");
		idProyecto = ((int) query.getSingleResult()) + 1;

		Proyecto p1 = new Proyecto(idProyecto, nombre);

		ses.getTransaction().begin();
		ses.save(p1);
		ses.getTransaction().commit();

		return true;
	}

	// ************ACTUALIZAR PROYECTO***************

	public boolean actualizarProyecto(String nombre, int idProyecto) throws Exception {

		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);

		int idPr = pr.getIdProyecto();

		if (idPr != idProyecto) {
			throw new Exception("ID de tarea incorrecto");
		}

		pr.setNombre(nombre);

		ses.beginTransaction();

		ses.update(pr);

		ses.getTransaction().commit();

		return true;
	}

	// *******************BORRAR PROYECTOS********************
	public boolean borrarProyecto(int idProyecto) {
		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);
		ses.getTransaction().begin();
		ses.delete(pr);
		ses.getTransaction().commit();

		return true;
	}

	// **********INSERTAR OBJETIVO***************
	public boolean insertarObjetivo(String nombre, String prioridad, int idProyecto) {

		int idObjetivo = 0;
		Query query = ses.createQuery("SELECT MAX(idObjetivo) FROM Objetivo");
		idObjetivo = ((int) query.getSingleResult()) + 1;
		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);
		Objetivo o1 = new Objetivo(idObjetivo, nombre, pr, prioridad);

		pr.añadirObjetivo(o1);

		ses.getTransaction().begin();

		ses.saveOrUpdate(pr);
		ses.getTransaction().commit();

		return true;
	}

	// ************ACTUALIZAR OBJETIVOS***************

	public boolean actualizarObjetivo(int idObjetivo, String prioridad, int idProyecto) throws Exception {

		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);

		Objetivo o1 = (Objetivo) ses.get(Objetivo.class, idObjetivo);

		int idPr = pr.getIdProyecto();

		if (idPr != idProyecto) {
			throw new Exception("ID de tarea incorrecto");
		}

		pr.modificarPrioridad(idObjetivo, prioridad);
		ses.beginTransaction();

		ses.update(pr);

		ses.getTransaction().commit();

		return true;
	}

	// ***************BORRAR OBJETIVOS**********************
	public boolean borrarObjetivo(int idObjetivo, int idProyecto) throws Exception {

		Objetivo o1 = (Objetivo) ses.get(Objetivo.class, idObjetivo);
		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);
		int idPr = pr.getIdProyecto();

		if (idPr != idProyecto) {
			throw new Exception("ID de tarea incorrecto");
		}

		pr.borrarObjetivo(o1);
		ses.getTransaction().begin();
		ses.update(pr);
		ses.delete(o1);
		ses.getTransaction().commit();

		return true;
	}

	// ***************AÑADIR USUARIO A PROYECTO**********************

	public boolean añadirUsuarioProyecto(int idUsuario, int idProyecto) {

		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);

		Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);

		UsuarioProyecto up = new UsuarioProyecto(us, pr);

		ses.getTransaction().begin();
		ses.save(up);
		ses.getTransaction().commit();

		return true;

	}

	// ***************ELIMINAR USUARIO DEL PROYECTO**********************

	public boolean eliminarUsuarioProyecto(int idUsuario, int idProyecto) {

		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);

		Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);

//		UsuarioProyecto up = (UsuarioProyecto) ses.get
		
		pr.borrarUsuarioProyecto(idUsuario);
		us.borrarUsuarioProyecto(idProyecto);

		ses.getTransaction().begin();
		ses.update(pr);
		ses.update(us);
		ses.getTransaction().commit();

		return true;
	}

	// ***************DEVOLVER PROYECTOS**********************

	public ArrayList<Proyecto> devolverProyecto() {
		Query query = ses.createQuery("SELECT p FROM Proyecto p ORDER BY idProyecto ASC");
		ArrayList<Proyecto> pro = new ArrayList<Proyecto>();
		pro = (ArrayList<Proyecto>) query.getResultList();
		return pro;
	}

	// ***************DEVOLVER USUARIOS DE UN PROYECTO**********************

	public ArrayList<UsuarioProyecto> devolverUsuario(int idProyecto) {
		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);

		ArrayList<UsuarioProyecto> aux = new ArrayList<UsuarioProyecto>();

		aux.addAll(pr.getUsuarios());

		return aux;
	}

	// ***************DEVOLVER PROYECTOS DE UN USUARIO**********************

	public ArrayList<UsuarioProyecto> devolverProyecto(int idUsuario) {
		Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);

		ArrayList<UsuarioProyecto> aux = new ArrayList<UsuarioProyecto>();

		aux.addAll(us.getProyectos());

		return aux;
	}

	// ***************DEVOLVER OBJETIVOS DE UN PROYECTO**********************

	public ArrayList<Objetivo> devolverObjetivo(int idProyecto) {
		Proyecto pr = (Proyecto) ses.get(Proyecto.class, idProyecto);

		ArrayList<Objetivo> aux = new ArrayList<Objetivo>();

		aux.addAll(pr.getIdObjetivo());

		return aux;
	}

	// **************************TRASH CODE***************************

//	public boolean insertarTarea(int idTarea, String nombre, String estado, int duracion, int idUser) {
//
//		Tarea t1 = new Tarea(idTarea, nombre, estado, duracion, idUser);
//
//		ses.getTransaction().begin();
//		ses.save(t1);
//		ses.getTransaction().commit();
//
//		return true;
//	}

//	public boolean actualizarEstado(int idTarea, String estado) {
//	Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
//	t1.setEstado(estado);
//	ses.getTransaction().begin();
//	ses.update(t1);
//	ses.getTransaction().commit();
//
//	return true;
//}

//public boolean actualizarEstadoRel(int idTarea, String estado, int idUsuario) {
//	Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
//	Usuario us = (Usuario) ses.get(Usuario.class, idUsuario);
//
//	t1.setEstado(estado);
//	us.modificarEstado(idTarea, estado);
//
//	ses.getTransaction();
//	ses.saveOrUpdate(us);
//	ses.saveOrUpdate(t1);
//
//	ses.getTransaction().commit();
//
//	return true;
//}
//	public boolean borrarTarea(int idTarea) {
//	Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
//	ses.getTransaction().begin();
//	ses.delete(t1);
//	ses.getTransaction().commit();
//
//	return true;
//}

//public boolean borrarTarea(int idTarea, int idUsuario) throws Exception {
//
//	// Query query = ses.createQuery("SELECT idUsuario FROM Tarea WHERE idTarea =
//	// "+idTarea);
//	Tarea t1 = (Tarea) ses.get(Tarea.class, idTarea);
//	int idUs = t1.getIdUser();
//
//	if (idUs != idUsuario) {
//		throw new Exception("ID de tarea incorrecto");
//	}
//
//	ses.getTransaction().begin();
//	ses.delete(t1);
//	ses.getTransaction().commit();
//
//	return true;
//}
//	public boolean insertarUsuario(int idUsuario, String apodo, String contraseña) {
//
//		Usuario u1 = new Usuario(idUsuario, apodo, contraseña);
//
//		ses.getTransaction().begin();
//		ses.save(u1);
//		ses.getTransaction().commit();
//
//		return true;
//	}
//	public ArrayList<Tarea> devolverMisTareas(int idUsuario) {
//
//		Query query = ses.createQuery("SELECT t FROM Tarea t WHERE idUser = " + idUsuario+" ORDER BY idTarea ASC");
//		ArrayList<Tarea> misTareas = new ArrayList<Tarea>();
//		misTareas = (ArrayList<Tarea>) query.getResultList();
//
//		return misTareas;
//	}
}
