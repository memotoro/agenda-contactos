package agenda.logica;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Esta es la Clase OperacionBD, encargada de establecer la conexión
 * con la base de datos, y administrar los objetos de Conexión.
 * @author Memo Toro
 */
public class OperacionBD {
	/**
	 * Variable de conexión para la base de datos.
	 */
	Connection cnConexion;
	/**
	 * Variable para las sentecias SQL a ejecutar.
	 */
	Statement stSentencia;
	/**
	 * Variable que se comporta como cursor con los resultados de las consultas ejecutadas sobre
	 * la base de datos.
	 */
	ResultSet rtResultado;
	/**
	 * Variable de tipo ConfigDAO para traerse los parametros de conexión a la base de datos. 
	 * En este caso la conexión es PostgreSQL. 
	 */
	ConfigDAO cConfig;
	/**
	 * Constructor de Clase OperacionBD
	 * Sirve para inicializar el controlador de conexión a la base de datos. 
	 * En este caso carga el Driver de JDBC para PostgreSQL.
	 * @exception Puede que el driver no se pueda cargar.
	 */
	public OperacionBD(){
		// Carga el Driver
		try{
			cConfig = new ConfigDAO();
			Class.forName(cConfig.getDriver());
			setConexion();
		}
		catch(Exception error) {
			System.out.println("No Se Pudo Cargar El Conector JDBC: " + error.getMessage());
		}
	}
	/**
	 * Establece la Conexión con los parametros de ConfigDAO.
	 * @exception No se puede crear la conexión con la base de datos.
	 */
	public void setConexion(){
		// Inicia el Driver, con el usuario y la contraseña. 
		try{
			cnConexion = DriverManager.getConnection(cConfig.getCadenaConexion(),cConfig.getUsuario(),cConfig.getContrasenia());
			setSentencia();
		}
		catch(SQLException error) {
			System.out.println( "No Se Pudo Conectar A La Base de Datos: " + error.getMessage());
		}
	}
	/**
	 * Establece una sentencia a partir de la conexión creada.
	 * @exception No se puede crear la sentencia. 
	 */
	public void setSentencia(){
		// Establece la sentencia con propidades de Scroll, para que se pueda navegar libremente los resultados de las consultas.
		try{
			stSentencia=getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		catch(SQLException error) {
			System.out.println( "No Se Pudo Crear La Sentencia: " + error.getMessage());
		}	
	}
	/**
	 * Ejecuta la sentencia y la almacena en un ResultSet.
	 * @param Temp
	 * Cadena con la sentencia a ejecutar.
	 * @exception No se puede establecer el resultado.
	 */
	public void setResultado(String Temp){
		try{
			rtResultado=getSentencia().executeQuery(Temp);	
		}
		catch(SQLException error) {
			System.out.println( "No Se Pudo Ëstablecer El Resultado De La Base De Datos: " + error.getMessage());
		}
	}
	/**
	 * Retorna la Conexión a la base de datos.
	 * @return Connection con la conexión a la base de datos.
	 */
	public Connection getConexion(){
		return cnConexion;
	}
	/**
	 * Retorna la sentencia establecida.
	 * @return Statement con las propiedaes estableidas por la conexión.
	 */
	public Statement getSentencia(){
		return stSentencia;
	}
	/**
	 * Retorna el resultado de la consulta.
	 * @return ResultSet producto de la consulta sobre la base de datos dado un Statement.
	 */
	public ResultSet getResultado(){
		return rtResultado;
	}
	/**
	 * Cierra la conexión con la base de datos.
	 * @exception No se puede cerrar la conexión con la base de datos.
	 */
	public void getCerrarConexion(){
		try{
			cnConexion.close();
		}
		catch(SQLException error) {
			System.out.println( "No Se Pudo Cerrar La Conexion: " + error.getMessage());
		}	
	}
}