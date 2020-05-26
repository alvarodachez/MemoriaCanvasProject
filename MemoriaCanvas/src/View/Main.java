package View;

import java.util.Scanner;

import Controller.Operations;

public class Main {

	public static Scanner keyboard = new Scanner(System.in);
	public static int ID_USUARIO_SESION;
	public static int OPT_MENU;

	public static void main(String[] args) {
		System.out.println("----------------------");
		System.out.println("MEMORIA CANVAS");
		System.out.println("POR: Alvaro Rueda\n\n");
		System.out.println("------------------------");
		
		do {

			// REGISTRAR O LOGEAR

			regLog();

			// SESION
			if (OPT_MENU != 3) {
				if (ID_USUARIO_SESION == 1) {
					sesionAdministrador();
				} else {
					sesion();
				}
			}

		} while (OPT_MENU != 3);

	}

	public static void regLog() {
		int flag = 0;

		System.out.println("1. Iniciar Sesion");
		System.out.println("2. Registrarse");
		System.out.println("3. Salir de la aplicacion");

		flag = readInteger("Introduce la opcion");

		if (flag == 3) {
			Operations.cerrarConexion();
			OPT_MENU = 3;
		} else {
			if (flag == 1) {
				iniciarSesion();
			} else {
				registrarse();
				iniciarSesion();

			}
		}

	}

	public static void registrarse() {
		boolean flag = false;

		do {
			System.out.println("------------------------------------");
			System.out.println("------------REGISTRESE---------------");
			System.out.println("-------------------------------------");
			// int idUsuario = readInteger("Introduce el id de usuario");
			String apodo = readString("Introduce tu nombre de usuario");
			String contraseña = readString("Introduce tu contraseña");

			try {
				System.out.println(Operations.crearUsuario(apodo, contraseña));
				flag = true;
			} catch (Exception e) {
				System.out.println("Error al crear el usuario");
				System.out.println(e.getMessage());
			}
		} while (flag == false);

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
				System.out.println("Ha iniciado sesion correctamente. ID_Sesion: " + ID_USUARIO_SESION);
				flag = true;
			} catch (Exception e) {
				System.out.println("ERROR AL INICIAR SESION");
				System.out.println(e.getMessage());
			}
		} while (flag == false);

	}

	public static void sesionAdministrador() {
		int flag = 0;
		int flag1 = 0;
		int flag2 = 0;

		do {
			writeMenuPrincipal();
			flag1 = readInteger("Introduce una opcion");

			switch (flag1) {
			case 1:
				do {
					writeMenuAdministrador();
					flag = readInteger("Elige una opcion");

					switch (flag) {
					case 1:
						try {
							System.out.println(Operations.devolverUsuarios());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}

						break;
					case 2:
						String apodo = readString("Introduce el apodo del usuario");
						try {
							System.out.println(Operations.devolverUsuarios(apodo));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}

						break;
					case 3:
						try {
							System.out.println(Operations.devolverTareas());
						} catch (Exception e) {
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

						switch (opt) {
						case 1:
							try {
								System.out.println(Operations.devolverMisTareas("pendiente"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							try {
								System.out.println(Operations.devolverMisTareas("realizando"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							try {
								System.out.println(Operations.devolverMisTareas("finalizada"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						}
						break;
					case 5:
						int idUsuario = readInteger("Introduce el id del usuario");
						try {
							System.out.println(Operations.devolverMisTareas(idUsuario));
						} catch (Exception e) {
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

						switch (opt) {
						case 1:
							try {
								System.out.println(Operations.devolverMisTareas(idUsuario, "pendiente"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							try {
								System.out.println(Operations.devolverMisTareas(idUsuario, "realizando"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							try {
								System.out.println(Operations.devolverMisTareas(idUsuario, "finalizada"));
							} catch (Exception e) {
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
						} catch (Exception e) {
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
						switch (opt) {
						case 1:
							try {
								System.out.println(Operations.actualizarEstado(idTarea, "pendiente", idUsuario));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							try {
								System.out.println(Operations.actualizarEstado(idTarea, "realizando", idUsuario));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							try {
								System.out.println(Operations.actualizarEstado(idTarea, "finalizada", idUsuario));
							} catch (Exception e) {
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
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 10:
						idUsuario = readInteger("Introduce el id del usuario");
						try {
							System.out.println(Operations.borrarUsuario(idUsuario));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 11:

						flag = 12;
						break;

					}
				} while (flag != 12);
				break;
			case 2:
				do {
					writeMenuAdministradorProyecto();
					flag2 = readInteger("Introduce una opcion");

					switch (flag2) {
					case 1:
						String nombre = readString("Introduce el nombre del proyecto");
						try {
							System.out.println(Operations.insertarProyecto(nombre));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 2:
						int idProyecto = readInteger("Introduce el id del proyecto");
						nombre = readString("Introduce el nuevo nombre del proyecto");
						try {
							System.out.println(Operations.actualizarProyecto(nombre, idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 3:
						idProyecto = readInteger("Introduce el id del proyecto");
						try {
							System.out.println(Operations.borrarProyecto(idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 4:
						nombre = readString("Introduce el nombre del objetivo");

						idProyecto = readInteger("Introduce el id del proyecto");
						try {
							System.out.println(Operations.insertarObjetivo(nombre, "NORMAL", idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 5:
						int idObjetivo = readInteger("Introduce el id del objetivo");
						idProyecto = readInteger("Introduce el id del proyecto");

						String prioridad = readString("Introduce la prioridad");
						try {
							System.out.println(Operations.actualizarObjetivo(idObjetivo, prioridad, idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 6:
						idProyecto = readInteger("Introduce el id del proyecto");
						idObjetivo = readInteger("Introduce el id del objetivo");

						try {
							System.out.println(Operations.borrarObjetivo(idObjetivo, idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 7:
						idProyecto = readInteger("Introduce el id del proyecto");
						int idUsuario = readInteger("Introduce el id del usuario");

						try {
							System.out.println(Operations.añadirUsuarioProyecto(idUsuario, idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 8:
						idProyecto = readInteger("Introduce el id del proyecto");
						idUsuario = readInteger("Introduce el id del usuario");

						try {
							System.out.println(Operations.eliminarUsuarioProyecto(idUsuario, idProyecto));
//							System.out.println("-------------------------------------\nEN DESARROLLO\n---------------------------------\n");
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 9:
						try {
							System.out.println(Operations.devolverProyectos());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 10:
						idProyecto = readInteger("Introduce el id del proyecto");

						try {
							System.out.println(Operations.devolverUsuarios(idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 11:
						idProyecto = readInteger("Introduce el id del proyecto");

						try {
							System.out.println(Operations.devolverObjetivo(idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 12:
						idUsuario = readInteger("Introduce el id del usuario");

						try {
							System.out.println(Operations.devolverProyectos(idUsuario));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 13:
						flag2 = 14;
						break;
					}
				} while (flag2 != 14);
				break;
			case 3:
				ID_USUARIO_SESION = -1;
				flag1 = 4;
				break;
			}
		} while (flag1 != 4);

	}

	public static void sesion() {

		int flag = 0;
		int flag1 = 0;
		int flag2 = 0;

		do {
			writeMenuPrincipal();
			flag1 = readInteger("Introduce una opcion");

			switch (flag1) {
			case 1:
				do {
					writeMenu();
					flag = readInteger("Elige una opcion");

					switch (flag) {
					case 1:
						String nombre = readString("Introduce un nombre de la tarea");
						int duracion = readInteger("Introduce la duracion estimada de la tarea");
						// int idUser = readInteger("Introduce el id del usuario (esto no funcionara
						// asi)");
						try {
							System.out.println(Operations.crearTarea(nombre, "pendiente", duracion, ID_USUARIO_SESION));
						} catch (Exception e) {
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
						switch (opt) {
						case 1:
							try {
								System.out
										.println(Operations.actualizarEstado(idTarea, "pendiente", ID_USUARIO_SESION));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							try {
								System.out
										.println(Operations.actualizarEstado(idTarea, "realizando", ID_USUARIO_SESION));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							try {
								System.out
										.println(Operations.actualizarEstado(idTarea, "finalizada", ID_USUARIO_SESION));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						}
						break;
					case 3:
						idTarea = readInteger("Introduce el id de la tarea a borrar");
						// System.out.println(Operations.borrarTarea(idTarea));
						try {
							System.out.println(Operations.borrarTarea(idTarea, ID_USUARIO_SESION));
						} catch (Exception e) {
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

						switch (opt) {
						case 1:
							try {
								System.out.println(Operations.devolverMisTareas(ID_USUARIO_SESION, "pendiente"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							try {
								System.out.println(Operations.devolverMisTareas(ID_USUARIO_SESION, "realizando"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							try {
								System.out.println(Operations.devolverMisTareas(ID_USUARIO_SESION, "finalizada"));
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							break;
						}
						break;
					case 6:

						flag = 7;
						break;

					}

				} while (flag != 7);
				break;
			case 2:
				do {
					writeMenuUsuarioProyecto();
					flag2 = readInteger("Introduce una opcion");

					switch (flag2) {
					case 1:

						try {
							System.out.println(Operations.devolverProyectos(ID_USUARIO_SESION));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 2:
						int idProyecto = readInteger("Introduce el id del proyecto");

						try {
							System.out.println(Operations.devolverObjetivo(idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 3:
						idProyecto = readInteger("Introduce el id del proyecto");

						try {
							System.out.println(Operations.devolverUsuarios(idProyecto));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 4:
						flag2 = 5;
						break;
					}
				} while (flag2 != 5);
				break;
			case 3:
				ID_USUARIO_SESION = -1;
				flag1 = 4;
				break;
			}
		} while (flag1 != 4);

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

	public static void writeMenuAdministradorProyecto() {
		System.out.println("-------MENU-------");
		System.out.println("1. Crear Proyecto");
		System.out.println("2. Modificar Proyecto");
		System.out.println("3. Borrar Proyecto");
		System.out.println("4. Añadir objetivo");
		System.out.println("5. Modificar objetivo");
		System.out.println("6. Borrar objetivo");
		System.out.println("7. Añadir usuario a un proyecto");
		System.out.println("8. Eliminar usuario de un proyecto");
		System.out.println("9. Listar proyectos");
		System.out.println("10. Listar usuarios de un proyecto");
		System.out.println("11. Listar objetivos de un proyecto");
		System.out.println("12. Ver los proyectos de un usuario");
		System.out.println("13. Salir");

	}

	public static void writeMenuUsuarioProyecto() {
		System.out.println("-------MENU-------");
		System.out.println("1. Ver mis proyectos");
		System.out.println("2. Ver objetivos de un proyecto");
		System.out.println("3. Ver compañeros de proyecto");
		System.out.println("4. Salir");
	}

	public static void writeMenuPrincipal() {
		System.out.println("-------MENU-------");
		System.out.println("1. Tareas");
		System.out.println("2. Proyectos");
		System.out.println("3. Salir");
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
