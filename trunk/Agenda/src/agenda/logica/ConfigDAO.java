package agenda.logica;
/**
 * Clase con los parametros de conexi�n para cada motor de base de datos.
 * Estos parametros son requeridos por el JDBC. En este caso PostgreSQL JDBC.
 * @author Memo Toro
 */
public class ConfigDAO {
	/**
	 * Variable para establecer el Driver.
	 */
	String sDriver;
	/**
	 * Variable para establecer la cadena de conexi�n a la base de datos.
	 */
	String sCadenaConexion;
	/**
	 * Variable para el Nombre de Usuario.
	 */
	String sUsuario;
	/**
	 * Variable de la Contrase�a del Usuario.
	 */
	String sContrasenia;
	/**
	 * Constructor de la Clase ConfigDAO.
	 * Inicializa y establece los valores para las variables de conexi�n.
	 * Estas variables deben cambiar si se cambia de motor de base de datos.
	 */
	public ConfigDAO(){
		sDriver="org.postgresql.Driver";
		sCadenaConexion="jdbc:postgresql://localhost:5432/agenda";
		sUsuario="postgres";
		sContrasenia="postgres";
	}
	/**
	 * M�todo que devuelve el driver de conexi�n.
	 * @return String con el driver.
	 */
	public String getDriver(){
		return sDriver;
	}
	/**
	 * M�todo que retorna toda la cadena de conexi�n.
	 * @return String con la cadena de conexi�n.
	 */
	public String getCadenaConexion(){
		return sCadenaConexion;
	}
	/**
	 * M�todo que retorna el usuario para la conexi�n.
	 * @return String con el nombre de usuario.
	 */
	public String getUsuario(){
		return sUsuario;
	}
	/**
	 * M�todo que retorna el password del usuario.
	 * @return Strinf con la contrase�a sin encriptar del usuario.
	 */
	public String getContrasenia(){
		return sContrasenia;
	}
}