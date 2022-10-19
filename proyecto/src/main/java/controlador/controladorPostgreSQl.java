package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.conexionPostgresql;
import modelo.dtoAlumno;
import modelo.consultasPostgreSQL;
import util.variablesConexionPostgreSQL;

/**
 * @author jpatloz -- Controlador de la base de datos. Se mostrará el controlador del proyecto     
 */

public class controladorPostgreSQl {

	public static void main(String[] args) {
		// Instanciamos los datos de conexion de utils y los desestructuramos
		conexionPostgresql conexion = new conexionPostgresql();

		// Instanciamos variablesConexionPostgreSQL para poder recoger los credenciales
		// de inicio de sesion
		variablesConexionPostgreSQL vc = new variablesConexionPostgreSQL();

		// Instanciamos las listas
		List<dtoAlumno> listAlumnos = new ArrayList<dtoAlumno>();

		// Hacemos su desestructuracion
		final String HOST = vc.getHost();
		final String PORT = vc.getPort();
		final String DB = vc.getDb();
		final String USER = vc.getUser();
		final String PASS = vc.getPass();

		// Hacemos la conexin
		Connection cn = conexion.añadirConexionPostgreSql(USER, PASS, PORT, HOST, DB);

		// Hacemos el insert para ver los datos por pantalla
		consultasPostgreSQL.insertNuevoAlumno(null, cn);
		// Una vez hecha la conexión hacemos la consulta
		consultasPostgreSQL.selectAllAlumnos(cn);

		// Método para imprimirlo por pantalla

		for (int i = 0; i < listAlumnos.size(); i++) {
			System.out.println("id: " + " " + listAlumnos.get(i).getId_alumno() + " || " + "nombre: " + " "
					+ listAlumnos.get(i).getNombre() + " || " + "apellidos: " + " " + listAlumnos.get(i).getApellidos() + " || "
					+ "email: " + " " + listAlumnos.get(i).getEmail());
		}
		
		//UPDATE
		//consultasPostgreSQL.cambiarDatosAlumno(cn);
		
		//DELETE
		//consultasPostgreSQL.eliminarAlumnos(cn);
		
		//CREATE
		//consultasPostgreSQL.crearTabla(cn);
		
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
