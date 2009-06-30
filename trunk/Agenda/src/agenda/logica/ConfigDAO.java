package agenda.logica;
/**
 * Clase con los parametros de conexión para cada motor de base de datos.
 * Estos parametros son requeridos por el JDBC. En este caso PostgreSQL JDBC.
 * @author Memo Toro
 */
public class ConfigDAO {
	/**
	 * Variable para establecer el Driver.
	 */
	String sDriver;
	/**
	 * Variable para establecer la cadena de conexión a la base de datos.
	 */
	String sCadenaConexion;
	/**
	 * Variable para el Nombre de Usuario.
	 */
	String sUsuario;
	/**
	 * Variable de la Contraseña del Usuario.
	 */
	String sContrasenia;
	/**
	 * Constructor de la Clase ConfigDAO.
	 * Inicializa y establece los valores para las variables de conexión.
	 * Estas variables deben cambiar si se cambia de motor de base de datos.
	 */
	public ConfigDAO(){
		sDriver="org.postgresql.Driver";
		sCadenaConexion="jdbc:postgresql://localhost:5432/agenda";
		sUsuario="postgres";
		sContrasenia="postgres";
	}
	/**
	 * Método que devuelve el driver de conexión.
	 * @return String con el driver.
	 */
	public String getDriver(){
		return sDriver;
	}
	/**
	 * Método que retorna toda la cadena de conexión.
	 * @return String con la cadena de conexión.
	 */
	public String getCadenaConexion(){
		return sCadenaConexion;
	}
	/**
	 * Método que retorna el usuario para la conexión.
	 * @return String con el nombre de usuario.
	 */
	public String getUsuario(){
		return sUsuario;
	}
	/**
	 * Método que retorna el password del usuario.
	 * @return Strinf con la contraseña sin encriptar del usuario.
	 */
	public String getContrasenia(){
		return sContrasenia;
	}
}