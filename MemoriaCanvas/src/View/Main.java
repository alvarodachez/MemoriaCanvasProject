package View;

import java.util.Scanner;

import Controller.Operations;


public class Main {

	public static Scanner keyboard = new Scanner(System.in);
	public static int ID_USUARIO_SESION;
	public static void main(String[] args) {
		
		//REGISTRAR O LOGEAR

		regLog();
		
		//SESION
		
		sesion();
		
		
	}
	
	public static void regLog() {
		int flag = 0;
		
		System.out.println("1. Iniciar Sesion");
		System.out.println("2. Registrarse");
		
		flag = readInteger("Introduce la opcion");
		
		if(flag == 1) {
			iniciarSesion();
		}else {
			registrarse();
			iniciarSesion();
			
		}
	}
	
	public static void registrarse() {
		System.out.println("------------------------------------");
		System.out.println("------------REGISTRESE---------------");
		System.out.println("-------------------------------------");
		//int idUsuario = readInteger("Introduce el id de usuario");
		String apodo = readString("Introduce tu nombre de usuario");
		String contraseña = readString("Introduce tu contraseña");
		
		try {
			System.out.println(Operations.crearUsuario(apodo, contraseña));
		}catch(Exception e) {
			System.out.println("Error al crear el usuario");
			System.out.println(e.getMessage());
		}
	}
	
	public static void iniciarSesion() {
		System.out.println("------------------------------------");
		System.out.println("------------INICIE SESION-------------");
		System.out.println("-------------------------------------");
		String apodo = readString("Introduzca su nombre de usuario");
		String contraseña = readString("Introduzca su contraseña");
		try {
			ID_USUARIO_SESION = Operations.iniciarSes(apodo, contraseña);
			System.out.println("Ha iniciado sesion correctamente. ID_Sesion: "+ID_USUARIO_SESION);
		}catch(Exception e) {
			System.out.println("ERROR AL INICIAR SESION");
			System.out.println(e.getMessage());
		}
	}

	public static void sesion() {

		int flag = 0;
		
		do {
			writeMenu();
			flag = readInteger("Elige una opcion");
			
			switch(flag) {
			case 1:
				String nombre = readString("Introduce un nombre de la tarea");
				int duracion = readInteger("Introduce la duracion estimada de la tarea");
				//int idUser = readInteger("Introduce el id del usuario (esto no funcionara asi)");
				try {
					System.out.println(Operations.crearTarea(nombre, "pendiente", duracion, ID_USUARIO_SESION));
				}catch(Exception e) {
					System.out.println("Error al crear la tarea");
				}
				break;
			case 2:
				String estado = readString("Introduce el estado de la tarea");
				int idTarea = readInteger("Introduce el id de la tarea a modificar");
				estado = readString("Introduce el nuevo estado de la tarea (pendiente,realizando,finalizada)");
				//System.out.println(Operations.actualizarEstado(idTarea, estado));
				try {
					System.out.println(Operations.actualizarEstado(idTarea, estado, ID_USUARIO_SESION));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				idTarea = readInteger("Introduce el id de la tarea a borrar");
				//System.out.println(Operations.borrarTarea(idTarea));
				try {
					System.out.println(Operations.borrarTarea(idTarea, ID_USUARIO_SESION));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println(Operations.devolverMisTareas(ID_USUARIO_SESION));
				break;
			case 5:
				Operations.cerrarConexion();
				flag = 6;
				break;
				
			}
			
		}while(flag!=6);
	}
	public static void writeMenu() {
		System.out.println("-------MENU-------");
		System.out.println("1. Insertar tarea");
		System.out.println("2. Actualizar estado de la tarea");
		System.out.println("3. Borrar tarea");
		System.out.println("4. Mostrar todas mis tareas");
		System.out.println("5. Exit");
		
	}

	public static String readString(String msg) {
		String sol;

		System.out.println(msg);
		sol = keyboard.nextLine();
		return sol;
	}

	public static int readInteger(String msg) {
		int sol;

		System.out.println(msg);
		sol = Integer.parseInt(keyboard.nextLine());
		return sol;
	}
}
