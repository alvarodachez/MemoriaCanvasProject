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
		if(ID_USUARIO_SESION == 1) {
			sesionAdministrador();
		}else {
			sesion();
		}
		
		
		
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
		boolean flag = false;
		
		do {
			System.out.println("------------------------------------");
			System.out.println("------------REGISTRESE---------------");
			System.out.println("-------------------------------------");
			//int idUsuario = readInteger("Introduce el id de usuario");
			String apodo = readString("Introduce tu nombre de usuario");
			String contraseña = readString("Introduce tu contraseña");
			
			try {
				System.out.println(Operations.crearUsuario(apodo, contraseña));
				flag = true;
			}catch(Exception e) {
				System.out.println("Error al crear el usuario");
				System.out.println(e.getMessage());
			}
		}while(flag == false);
		
	}
	
	public static void iniciarSesion() {
		
		boolean flag = false;
		
		
		do {
			System.out.println("------------------------------------");
			System.out.println("------------INICIE SESION-------------");
			System.out.println("-------------------------------------");
			String apodo = readString("Introduzca su nombre de usuario");
			String contraseña = readString("Introduzca su contraseña");
			try {
				ID_USUARIO_SESION = Operations.iniciarSes(apodo, contraseña);
				System.out.println("Ha iniciado sesion correctamente. ID_Sesion: "+ID_USUARIO_SESION);
				flag = true;
			}catch(Exception e) {
				System.out.println("ERROR AL INICIAR SESION");
				System.out.println(e.getMessage());
			}
		}while(flag == false);
		
	}
	
	public static void sesionAdministrador() {
		int flag = 0;
		
		do {
			writeMenuAdministrador();
			flag = readInteger("Elige una opcion");
			
			switch(flag) {
			case 1:
				try {
					System.out.println(Operations.devolverUsuarios());
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 2:
				String apodo = readString("Introduce el apodo del usuario");
				try {
					System.out.println(Operations.devolverUsuarios(apodo));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				try {
					System.out.println(Operations.devolverTareas());
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				int opt = 0;
				System.out.println("\t|");
				System.out.println("\t|-1. Tareas pendientes");
				System.out.println("\t|");
				System.out.println("\t|-2. Tareas realizando");
				System.out.println("\t|");
				System.out.println("\t|-3. Tareas finalizadas\n");
				opt = readInteger("Introduce la opcion");
				
				switch(opt) {
				case 1:
					try {
						System.out.println(Operations.devolverMisTareas("pendiente"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						System.out.println(Operations.devolverMisTareas("realizando"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println(Operations.devolverMisTareas("finalizada"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				break;
			case 5:
				int idUsuario = readInteger("Introduce el id del usuario");
				try {
					System.out.println(Operations.devolverMisTareas(idUsuario));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				idUsuario = readInteger("Introduce el id del usuario");
				System.out.println("\t|");
				System.out.println("\t|-1. Tareas pendientes");
				System.out.println("\t|");
				System.out.println("\t|-2. Tareas realizando");
				System.out.println("\t|");
				System.out.println("\t|-3. Tareas finalizadas\n");
				opt = readInteger("Introduce la opcion");
				
				switch(opt) {
				case 1:
					try {
						System.out.println(Operations.devolverMisTareas(idUsuario,"pendiente"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						System.out.println(Operations.devolverMisTareas(idUsuario,"realizando"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println(Operations.devolverMisTareas(idUsuario,"finalizada"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				break;
			case 7:
				idUsuario = readInteger("Introduce el id del usuario");
				String nombre = readString("Introduce un nombre de la tarea");
				int duracion = readInteger("Introduce la duracion estimada de la tarea");
				try {
					System.out.println(Operations.crearTarea(nombre, "pendiente", duracion, idUsuario));
				}catch(Exception e) {
					System.out.println("Error al crear la tarea");
				}
				break;
			case 8:
				idUsuario = readInteger("Introduce el id del usuario");
				int idTarea = readInteger("Introduce el id de la tarea");
				System.out.println("\t|");
				System.out.println("\t|-1. Cambiar a pendiente");
				System.out.println("\t|");
				System.out.println("\t|-2. Cambiar a realizando");
				System.out.println("\t|");
				System.out.println("\t|-3. Cambiar a finalizada\n");
				opt = readInteger("Introduce la opcion");
				switch(opt) {
				case 1:
					try {
						System.out.println(Operations.actualizarEstado(idTarea, "pendiente", idUsuario));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						System.out.println(Operations.actualizarEstado(idTarea, "realizando", idUsuario));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println(Operations.actualizarEstado(idTarea, "finalizada", idUsuario));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				
				break;
			case 9:
				idUsuario = readInteger("Introduce el id del usuario");
				idTarea = readInteger("Introduce el id de la tarea");
				try {
					Operations.borrarTarea(idTarea, idUsuario);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 10:
				idUsuario = readInteger("Introduce el id del usuario");
				try {
					System.out.println(Operations.borrarUsuario(idUsuario));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 11:
				Operations.cerrarConexion();
				flag = 12;
				break;
				
			}
		}while(flag!=12);
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
				int idTarea = readInteger("Introduce el id de la tarea");
				System.out.println("\t|");
				System.out.println("\t|-1. Cambiar a pendiente");
				System.out.println("\t|");
				System.out.println("\t|-2. Cambiar a realizando");
				System.out.println("\t|");
				System.out.println("\t|-3. Cambiar a finalizada\n");
				int opt = readInteger("Introduce la opcion");
				switch(opt) {
				case 1:
					try {
						System.out.println(Operations.actualizarEstado(idTarea, "pendiente", ID_USUARIO_SESION));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						System.out.println(Operations.actualizarEstado(idTarea, "realizando", ID_USUARIO_SESION));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println(Operations.actualizarEstado(idTarea, "finalizada", ID_USUARIO_SESION));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
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
				System.out.println("\t|");
				System.out.println("\t|-1. Tareas pendientes");
				System.out.println("\t|");
				System.out.println("\t|-2. Tareas realizando");
				System.out.println("\t|");
				System.out.println("\t|-3. Tareas finalizadas\n");
				opt = readInteger("Introduce la opcion");
				
				switch(opt) {
				case 1:
					try {
						System.out.println(Operations.devolverMisTareas(ID_USUARIO_SESION,"pendiente"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						System.out.println(Operations.devolverMisTareas(ID_USUARIO_SESION,"realizando"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println(Operations.devolverMisTareas(ID_USUARIO_SESION,"finalizada"));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				break;
			case 6:
				Operations.cerrarConexion();
				flag = 7;
				break;
				
			}
			
		}while(flag!=7);
	}
	public static void writeMenu() {
		System.out.println("-------MENU-------");
		System.out.println("1. Insertar tarea");
		System.out.println("2. Actualizar estado de la tarea");
		System.out.println("3. Borrar tarea");
		System.out.println("4. Mostrar todas mis tareas");
		System.out.println("5. Mostrar tareas segun estado");
		System.out.println("6. Salir");
		
	}
	public static void writeMenuAdministrador() {
		System.out.println("-------MENU-------");
		System.out.println("1. Visualizar todos los usuarios");
		System.out.println("2. Usuario segun su apodo");
		System.out.println("3. Visualizar todas las tareas");
		System.out.println("4. Visualizar tareas segun el estado");
		System.out.println("5. Visualizar las tareas de un usuario");
		System.out.println("6. Visualizar las tareas de un usuario segun su estado");
		System.out.println("7. Insertar tarea a un usuario");
		System.out.println("8. Modificar el estado de una tarea a un usuario");
		System.out.println("9. Borrar tarea de un usuario");
		System.out.println("10. Borrar usuario");
		System.out.println("11. Salir");
		
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
