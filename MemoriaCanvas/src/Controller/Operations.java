package Controller;

import javax.persistence.Query;

import Model.Conexion;
import Model.Objetivo;
import Model.Proyecto;
import Model.Tarea;
import Model.Usuario;
import Model.UsuarioProyecto;

public class Operations {

	private static Conexion conn = Conexion.getSession();

	// *******************CREAR TAREAS*****************************

	public static String crearTarea(String nombre, String estado, int duracion, int idUser) {

		if (conn.insertarTarea(nombre, estado.toUpperCase(), duracion, idUser) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

	// ******************ACTUALIZAR TAREAS*******************************

	public static String actualizarEstado(int idTarea, String estado, int idUsuario) throws Exception {

		if (conn.actualizarEstado(idTarea, estado.toUpperCase(), idUsuario) == true) {
			return "Estado actualizado con exito";
		} else {
			return "Error al actualizar el estado";
		}
	}

	// *****************BORRAR TAREAS***************************************

	public static String borrarTarea(int idTarea, int idUsuario) throws Exception {
		if (conn.borrarTarea(idTarea, idUsuario) == true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}

	// *****************CREAR USUARIOS******************************************

	public static String crearUsuario(String apodo, String contraseña) {

		if (conn.insertarUsuario(apodo, contraseña) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

	public static int iniciarSes(String apodo, String contraseña) {
		return conn.devolverIdIniciarSesion(apodo, contraseña);
	}

	// *****************DEVOLVER TAREAS**********************************

	// *****Devolver todas las tareas de un usuario******
	public static String devolverMisTareas(int idUsuario) {
		StringBuilder aux = new StringBuilder();

		for (Tarea t : conn.devolverMisTareas(idUsuario)) {

			aux.append("\n\tID: " + t.getIdTarea() + "\n\tNOMBRE: " + t.getNombre() + "\n\tESTADO: " + t.getEstado()
					+ "\n\tDURACION: " + t.getDuracion() + "\n\n");
			aux.append("-------------------------------------------------------------------");

		}
		return "-------------------------------------------------------------------\n--------------------------------TAREAS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}

	// ******Devolver todas las tareas segun su estado*****
	public static String devolverMisTareas(String status) {
		StringBuilder aux = new StringBuilder();

		for (Tarea t : conn.devolverMisTareas(status)) {

			aux.append("\n\tID: " + t.getIdTarea() + "\n\tNOMBRE: " + t.getNombre() + "\n\tESTADO: " + t.getEstado()
					+ "\n\tDURACION: " + t.getDuracion() + "\n\n");
			aux.append("-------------------------------------------------------------------");

		}
		return "-------------------------------------------------------------------\n--------------------------------TAREAS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}

	// *****Devolver tareas de un usuario segun su estado****
	public static String devolverMisTareas(int idUser, String status) {
		StringBuilder aux = new StringBuilder();

		for (Tarea t : conn.devolverMisTareas(idUser, status)) {

			aux.append("\n\tID: " + t.getIdTarea() + "\n\tNOMBRE: " + t.getNombre() + "\n\tESTADO: " + t.getEstado()
					+ "\n\tDURACION: " + t.getDuracion() + "\n\n");
			aux.append("-------------------------------------------------------------------");

		}
		return "-------------------------------------------------------------------\n--------------------------------TAREAS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}

	// *****Devolver todas las tareas******
	public static String devolverTareas() {
		StringBuilder aux = new StringBuilder();

		for (Tarea t : conn.devolverTareas()) {

			aux.append("\n\tID: " + t.getIdTarea() + "\n\tNOMBRE: " + t.getNombre() + "\n\tESTADO: " + t.getEstado()
					+ "\n\tDURACION: " + t.getDuracion() + "\n\n");
			aux.append("-------------------------------------------------------------------");

		}
		return "-------------------------------------------------------------------\n--------------------------------TAREAS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}

	// *****************DEVOLVER USUARIOS******************************************
	// ****Devolver todos los usuarios********
	public static String devolverUsuarios() {
		StringBuilder aux = new StringBuilder();

		for (Usuario u : conn.devolverUsuarios()) {
			aux.append("\n\tID: " + u.getIdUsuario() + "\n\tAPODO: " + u.getApodo() + "\n\n");
			aux.append("-------------------------------------------------------------------");
		}

		return "-------------------------------------------------------------------\n--------------------------------USUARIOS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}

	// *******Devolver usuario segun su apodo*****
	public static String devolverUsuarios(String apodo) {
		StringBuilder aux = new StringBuilder();

		for (Usuario u : conn.devolverUsuarios(apodo)) {
			aux.append("\n\tID: " + u.getIdUsuario() + "\n\tAPODO: " + u.getApodo() + "\n\n");
			aux.append("-------------------------------------------------------------------");
		}

		return "-------------------------------------------------------------------\n--------------------------------USUARIOS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}

	// *******************BORRAR USUARIOS********************
	public static String borrarUsuario(int idUsuario) {
		if (conn.borrarUsuario(idUsuario) == true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}

	// *****************CERRAR CONEXION***************
	public static void cerrarConexion() {
		conn.closeSession();
	}

	// *******************INSERTAR PROYECTO********************

	public static String insertarProyecto(String nombre) {

		if (conn.insertarProyecto(nombre) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

	// ******************ACTUALIZAR PROYECTO*******************************

	public static String actualizarProyecto(String nombre, int idProyecto) throws Exception {

		if (conn.actualizarProyecto(nombre, idProyecto) == true) {
			return "Estado actualizado con exito";
		} else {
			return "Error al actualizar el estado";
		}
	}

	// *******************BORRAR PROYECTOS********************
	public static String borrarProyecto(int idProyecto) {
		if (conn.borrarProyecto(idProyecto) == true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}

	// *******************INSERTAR OBJETIVOS*****************************

	public static String insertarObjetivo(String nombre, String prioridad, int idProyecto) {

		if (conn.insertarObjetivo(nombre, prioridad.toUpperCase(), idProyecto) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

	// ******************ACTUALIZAR OBJETIVOS*******************************

	public static String actualizarObjetivo(int idObjetivo, String prioridad, int idProyecto) throws Exception {

		if (conn.actualizarObjetivo(idObjetivo, prioridad, idProyecto) == true) {
			return "Estado actualizado con exito";
		} else {
			return "Error al actualizar el estado";
		}
	}

	// *****************BORRAR OBJETIVO***************************************

	public static String borrarObjetivo(int idObjetivo, int idProyecto) throws Exception {
		if (conn.borrarObjetivo(idObjetivo, idProyecto) == true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}

	// *******************INSERTAR USARIO A PROYECTO*****************************

	public static String añadirUsuarioProyecto(int idUsuario, int idProyecto) {

		if (conn.añadirUsuarioProyecto(idUsuario, idProyecto) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

	// *****************BORRAR USUARIO DE UN
	// PROYECTO***************************************

	public static String eliminarUsuarioProyecto(int idUsuario, int idProyecto) throws Exception {
		if (conn.eliminarUsuarioProyecto(idUsuario, idProyecto) == true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}
	// *****************DEVOLVER PROYECTOS***************************************

	public static String devolverProyectos() {
		StringBuilder aux = new StringBuilder();

		for (Proyecto p : conn.devolverProyecto()) {
			aux.append("\n\tID: " + p.getIdProyecto() + "\n\tNOMBRE: " + p.getNombre() + "\n\n");
			aux.append("-------------------------------------------------------------------");
		}

		return "-------------------------------------------------------------------\n--------------------------------PROYECTOS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}
	// *****************DEVOLVER PROYECTOS DE UN
	// USUARIO***************************************

	public static String devolverUsuarios(int idProyecto) {
		StringBuilder aux = new StringBuilder();
		String aux1 = new String();

		for (UsuarioProyecto up : conn.devolverUsuario(idProyecto)) {
			aux1 = up.getIdProyecto().getNombre();
			aux.append("\n\tID: " + up.getIdUsuario().getIdUsuario() + "\n\tAPODO: " + up.getIdUsuario().getApodo() + "\n\n");
			aux.append("-------------------------------------------------------------------");
		}

		return "-------------------------------------------------------------------\n--------------------------------USUARIOS\nID_PROYECTO: "+idProyecto+"\nNOMBRE_PROYECTO: "+aux1+"\n--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}
	// *****************DEVOLVER OBJETIVOS DE UN PROYECTO***************************************
	public static String devolverObjetivo(int idProyecto) {
		StringBuilder aux = new StringBuilder();
		String aux1 = new String();

		for (Objetivo o : conn.devolverObjetivo(idProyecto)) {
			aux1 = o.getIdProyect().getNombre();

			aux.append("\n\tID: " + o.getIdObjetivo() + "\n\tNOMBRE: " + o.getNombre() + "\n\tPRIORIDAD: " + o.getPrioridad()
					+"\n\n");
			aux.append("-------------------------------------------------------------------");

		}
		return "-------------------------------------------------------------------\n--------------------------------OBJETIVOS\nID_PROYECTO: "+idProyecto+"\nNOMBRE_PROYECTO: "+aux1+"\n--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}
	
	// *****************DEVOLVER PROYECTOS DE UN
		// USUARIOS***************************************
	public static String devolverProyectos(int idUsuario) {
		StringBuilder aux = new StringBuilder();
		String aux1 = new String();

		for (UsuarioProyecto up : conn.devolverProyecto(idUsuario)) {
			aux1 = up.getIdUsuario().getApodo();
			aux.append("\n\tID: " + up.getIdProyecto().getIdProyecto() + "\n\tAPODO: " + up.getIdProyecto().getNombre() + "\n\n");
			aux.append("-------------------------------------------------------------------");
		}

		return "-------------------------------------------------------------------\n--------------------------------PROYECTOS\nID_USUARIO: "+idUsuario+"\nNOMBRE_USUARIO: "+aux1+"\n--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}
	// TRASH CODE

//	public static String crearUsuario(int idUsuario, String apodo, String contraseña) {
//
//		if (conn.insertarUsuario(idUsuario, apodo, contraseña) == true) {
//			return "Insertado con exito";
//		} else {
//			return "Error al insertar";
//		}
//	}
//	public static String crearTarea(int idTarea, String nombre, String estado, int duracion, int idUser) {
//
//		if (conn.insertarTarea(idTarea, nombre, estado.toUpperCase(), duracion, idUser) == true) {
//			return "Insertado con exito";
//		} else {
//			return "Error al insertar";
//		}
//	}
//	public static String crearTarea(int idTarea, String nombre, String estado, int duracion, int idUser) {
//
//		if (conn.insertarTarea(idTarea, nombre, estado.toUpperCase(), duracion, idUser) == true) {
//			return "Insertado con exito";
//		} else {
//			return "Error al insertar";
//		}
//	}
//	public static String actualizarEstadoRel(int idTarea, String estado, int idUsuario) {
//	if (conn.actualizarEstadoRel(idTarea, estado.toUpperCase(), idUsuario) == true) {
//		return "Estado actualizado con exito";
//	} else {
//		return "Error al actualizar el estado";
//	}
//}
//	public static String borrarTarea(int idTarea) {
//	if (conn.borrarTarea(idTarea) == true) {
//		return "Borrado con exito";
//	} else {
//		return "Error al borrar tarea";
//	}
//}
}
