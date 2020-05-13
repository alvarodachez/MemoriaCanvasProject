package Controller;

import Model.Conexion;
import Model.Tarea;
import Model.Usuario;

public class Operations {

	private static Conexion conn = Conexion.getSession();

	public static String crearTarea(int idTarea, String nombre, String estado, int duracion, int idUser) {

		if (conn.insertarTarea(idTarea, nombre, estado.toUpperCase(), duracion, idUser) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

	public static String crearTarea(String nombre, String estado, int duracion, int idUser) {

		if (conn.insertarTarea(nombre, estado.toUpperCase(), duracion, idUser) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

	public static String actualizarEstado(int idTarea, String estado) {
		if (conn.actualizarEstado(idTarea, estado.toUpperCase()) == true) {
			return "Estado actualizado con exito";
		} else {
			return "Error al actualizar el estado";
		}
	}

	public static String actualizarEstado(int idTarea, String estado, int idUsuario) throws Exception {
		if (conn.actualizarEstado(idTarea, estado.toUpperCase(), idUsuario) == true) {
			return "Estado actualizado con exito";
		} else {
			return "Error al actualizar el estado";
		}
	}

	public static String borrarTarea(int idTarea) {
		if (conn.borrarTarea(idTarea) == true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}

	public static String borrarTarea(int idTarea, int idUsuario) throws Exception {
		if (conn.borrarTarea(idTarea, idUsuario) == true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}

	public static String crearUsuario(int idUsuario, String apodo, String contraseña) {

		if (conn.insertarUsuario(idUsuario, apodo, contraseña) == true) {
			return "Insertado con exito";
		} else {
			return "Error al insertar";
		}
	}

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
	
	public static String devolverMisTareas(int idUser, String status) {
		StringBuilder aux = new StringBuilder();

		for (Tarea t : conn.devolverMisTareas(idUser,status)) {

			aux.append("\n\tID: " + t.getIdTarea() + "\n\tNOMBRE: " + t.getNombre() + "\n\tESTADO: " + t.getEstado()
					+ "\n\tDURACION: " + t.getDuracion() + "\n\n");
			aux.append("-------------------------------------------------------------------");

		}
		return "-------------------------------------------------------------------\n--------------------------------TAREAS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}
	
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

	public static String devolverUsuarios() {
		StringBuilder aux = new StringBuilder();

		for (Usuario u : conn.devolverUsuarios()) {
			aux.append("\n\tID: " + u.getIdUsuario() + "\n\tAPODO: " + u.getApodo() + "\n\n");
			aux.append("-------------------------------------------------------------------");
		}

		return "-------------------------------------------------------------------\n--------------------------------USUARIOS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}
	
	public static String devolverUsuarios(String apodo) {
		StringBuilder aux = new StringBuilder();

		for (Usuario u : conn.devolverUsuarios(apodo)) {
			aux.append("\n\tID: " + u.getIdUsuario() + "\n\tAPODO: " + u.getApodo() + "\n\n");
			aux.append("-------------------------------------------------------------------");
		}

		return "-------------------------------------------------------------------\n--------------------------------USUARIOS--------------------------------\n"
				+ "-------------------------------------------------------------------\n" + aux.toString();
	}
	
	public static String borrarUsuario(int idUsuario) {
		if (conn.borrarUsuario(idUsuario)== true) {
			return "Borrado con exito";
		} else {
			return "Error al borrar tarea";
		}
	}

	public static void cerrarConexion() {
		conn.closeSession();
	}

}
