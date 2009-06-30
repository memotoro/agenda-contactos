package agenda.logica;

import java.sql.SQLException;
import java.util.Iterator;
/**
 * Clase encargada de administrar la información de las personas a editar en la agenda.
 * @author Memo Toro
 */
public class Editar {
	/**
	 * Variable con las sentencias SQL. 
	 */
	private String sSentencia;
	/**
	 * Variable para almacenar el Id de la pesona a Editar.
	 */
	private String sIdPersona;
	/**
	 * Variable para establecer la Conexión con la base de datos.
	 */
	private OperacionBD oConexion;
	/**
	 * Variable para recorrer los List.
	 */
	Iterator it;
	/**
	 * Constructor de Editar. 
	 * Inicializa la Conexión.
	 */
	public Editar(){
		oConexion = new OperacionBD();
	}
	/**
	 * Método encargado de actualizar los datos de una persona en la base de datos.
	 * El método borra los datos de correos y celulares y vuelve y los ingresa a las respectivas tablas modificados.
	 * @param Modificada
	 * Persona con los datos modificados y a actualizar.
	 * @param Original
	 * Persona con los valores sin modificar, con los valores antes de la edición.
	 * @return boolean para conocer si se edito o no la persona en la base de datos.
	 */
	public boolean ActualizarDatos(Persona Modificada,Persona Original){
		try{
			// Realiza la consulta para obtener el Id de la persona sin modificar los datos.
			sSentencia = "SELECT ID_PERSONA FROM PERSONA WHERE NOMBRES='"+Original.getNombres()+"' AND APELLIDOS='"+Original.getApellidos()+"' AND TELEFONO_FIJO='"+Original.getTelefonoFijo()+"'";
			oConexion.setResultado(sSentencia);
			oConexion.getResultado().next();
			// Obtiene del ResultSet el Id
			sIdPersona = oConexion.getResultado().getString("ID_PERSONA");
			// Realiza la actualización de los datos de la Persona.
			sSentencia = "UPDATE PERSONA SET NOMBRES='"+Modificada.getNombres()+"',APELLIDOS='"+Modificada.getApellidos()+"',TELEFONO_FIJO='"+Modificada.getTelefonoFijo()+"' WHERE ID_PERSONA='"+sIdPersona+"'";
			oConexion.getSentencia().execute(sSentencia);
			it = Original.getCelulares().iterator();
			// Realiza el bucle para borrar los celulares de la persona.
			while(it.hasNext()){
				sSentencia = "DELETE FROM CELULAR WHERE TELEFONO_CELULAR='"+it.next()+"' AND ID_PERSONA='"+sIdPersona+"'";
				oConexion.getSentencia().execute(sSentencia);
			}
			it = Modificada.getCelulares().iterator();
			// Realiza el bucle para insertar los datos de celulares modificados de la persona.
			while(it.hasNext()){
				sSentencia = "INSERT INTO CELULAR(TELEFONO_CELULAR,ID_PERSONA) VALUES('"+it.next()+"','"+sIdPersona+"')";
				oConexion.getSentencia().execute(sSentencia);
			}
			it = Original.getCorreos().iterator();
			// Realiza el bucle para borrar los correos de la persona.
			while(it.hasNext()){
				sSentencia = "DELETE FROM CORREO WHERE CORREO='"+it.next()+"' AND ID_PERSONA='"+sIdPersona+"'";
				oConexion.getSentencia().execute(sSentencia);
			}	
			it = Modificada.getCorreos().iterator();
			// Realiza el bucle para insertar los datos de correos modificados de la persona.
			while(it.hasNext()){
				sSentencia = "INSERT INTO CORREO(CORREO,ID_PERSONA) VALUES('"+it.next()+"','"+sIdPersona+"')";
				oConexion.getSentencia().execute(sSentencia);
			}	
			oConexion.getCerrarConexion();
			return true;
		}
		catch(SQLException error) {
			System.out.println( "No se pudo Insertar los Datos en la Base de Datos." + error.getMessage());
			return false;
		}		
	}	
}