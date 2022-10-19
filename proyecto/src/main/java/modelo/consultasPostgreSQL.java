package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.dtoAlumno;
import modelo.dtoADto;

/**
 * @author jpatloz -- Consultas necesarias a la BBDD de PostgreSQL
 */

public class consultasPostgreSQL {

	public static ArrayList<dtoAlumno> selectAllAlumnos(Connection conexionGenerada) {
		
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Entra en selectAllAlumnos");
		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<dtoAlumno> listAlumnos = new ArrayList<>();
		
		try {
			
			//Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			//Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"proyectoEclipse\".\"alumnos\"");
			System.out.println("--------------------");
		    
			//Llamada a la conversión a dtoAlumno
			listAlumnos = dtoADto.resultsetAdtoAlumno(resultadoConsulta);
			int i = listAlumnos.size();
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Número alumnos: "+i);
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Cierre declaración y resultado");				
		    resultadoConsulta.close();
		    declaracionSQL.close();
		    			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-conexionPostgresql-main] Error generando la declaracionSQL: " + e);
			return listAlumnos;
			
		}
		return listAlumnos;
		
	}
	
	// INSERT Alumno - Método que inserta nuevos sobre la tabla alumnos de la BBDD.
		public static void insertNuevoAlumno(ResultSet consulta, Connection conexionGenerada) {
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Entra en insertNuevoAlumno");
			Statement declaracionSQL = null;
			
			try {
				System.out.println("HACEMOS El INSERT");
				System.out.println("--------------------");
				// Abrimos la declaracion
				declaracionSQL = conexionGenerada.createStatement();
				// Se hace y ejecuta la consulta
				consulta = declaracionSQL.executeQuery("INSERT INTO \"proyectoEclipse\".\"Alumnos\" (nombre,apellidos,email)"
								+ "VALUES('Nombre','Apellido','ejemplo@gmail.com')");

			} catch (SQLException e) {

				System.out.println("Error generando la declaracionSQL (insert): " + e);

			}
		}
		
		// UPDATE alumno -- Método que actualiza los datos de un alumno en la BBDD
				public static void cambiarDatosAlumno(Connection conexion) {
					// Declaramos el state y la respuesta
					Statement declaracionSQL = null;
					ResultSet resultadoConsulta = null;
					if (conexion != null) {

						try {
							System.out.println("HACEMOS El UPDATE");
							// Abrimos la declaracion
							declaracionSQL = conexion.createStatement();
							// Se hace y ejecuta la consulta
							resultadoConsulta = declaracionSQL.executeQuery(
									"UPDATE \"proyectoEclipse\".\"Alumnos\" SET nombre='Jesús', apellidos='PatricioLozano', email='jesuspatriciolozano@gmail.com' WHERE id=1 ");

						} catch (SQLException e) {
							System.out.println("Error generando la declaracionSQL (UPDATE): " + e);

						}
					}
				}
				
	
		// DELETE alumno -- Método que elimina un alumno de la BBDD

		public static void eliminarAlumnos(Connection conexion) {
			// Declaramos el state y la respuesta
			Statement declaracionSQL = null;
			ResultSet resultadoConsulta = null;
			if (conexion != null) {

				try {
					System.out.println("HACEMOS El DELETE");
					System.out.println("--------------------");
					// Abrimos la declaracion
					declaracionSQL = conexion.createStatement();
					// Se hace y ejecuta la consulta
					resultadoConsulta = 
							declaracionSQL.executeQuery("DELETE FROM \"proyectoEclipse\".\"Alumnos\" WHERE id='3'");

				} catch (SQLException e) {

					System.out.println("Error generando la declaracionSQL (DELETE): " + e);

				}
			}
		}
		
		
		
		//CREATE TABLE -- Método que crea una nueva tabla de la base de datos 

		public static void crearTabla(Connection conexion) {
			// Hacemos la declaracion del CREATE
			Statement declaracionSQL = null;
			if (conexion != null) {

				try {
					System.out.println("CREAMOS TABLA NUEVA");
					System.out.println("--------------------");
					// Abrimos la declaracion
					declaracionSQL = conexion.createStatement();
					// Se hace y ejecuta la consulta
					declaracionSQL.executeUpdate(
							"CREATE TABLE IF NOT EXISTS ejemplo (proyectoEclipse_Ejemplo CHARACTER VARYING NOT NULL)");
				} catch (SQLException e) {
					System.out.println("Error generando la declaracionSQL (CREATE TABLE): " + e);

				}
			}
		}

}
