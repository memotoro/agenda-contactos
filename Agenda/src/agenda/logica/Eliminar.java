package agenda.logica;

import java.util.Iterator;
/**
 * Clase destinada a Eliminar Personas de la Base de Datos.
 * @author Memo Toro
 */
public class Eliminar {
	/**
	 * Variable para establecer la Conexión.
	 */
	OperacionBD oConexion;
	/**
	 * Variable para almacenar la sentencia a ejecutar.
	 */
	String sSentencia;
	/**
	 * Variable para almacenar el Id de la persona a eliminar de la agenda.
	 */
	String sIdPersona;
	/**
	 * Variable para recorrer los List.
	 */
	Iterator it;
	/**
	 * Constructor de la Clase Eliminar.
	 * Inicializa la Conexión.
	 */
	public Eliminar(){
		oConexion = new OperacionBD();
	}
	/**
	 * Métoco encargado de eliminar los registros asociados a una persona.
	 * @param Temp
	 * Persona que se desea eliminar de la base de datos.
	 */
	public void Borrar(Persona Temp){	
		try{
			// Se crea una sentencia SQL para obtener primero el Id de la persona de la base de datos a partir de los datos de la persona.
			sSentencia = "SELECT ID_PERSONA FROM PERSONA WHERE NOMBRES='"+Temp.getNombres()+"' AND APELLIDOS='"+Temp.getApellidos()+"' AND TELEFONO_FIJO='"+Temp.getTelefonoFijo()+"'";
			oConexion.setResultado(sSentencia);
			oConexion.getResultado().next();
			sIdPersona = oConexion.getResultado().getString("ID_PERSONA");
			it = Temp.getCelulares().iterator();
			 // Sentencia y bucle While para borrar primero los celulares de la persona. Primero celulares para respetar integridad referencial.  
			while(it.hasNext()){				
				sSentencia = "DELETE FROM CELULAR WHERE TELEFONO_CELULAR='"+(Long)it.next()+"' AND ID_PERSONA='"+sIdPersona+"'";
				oConexion.getSentencia().execute(sSentencia);
			}
			it = Temp.getCorreos().iterator();
			// Sentencia y bucle While para borrar los correso de la persona. Se borran luego de celulares para respetar integridad referencial.  
			while(it.hasNext()){				
				sSentencia = "DELETE FROM CORREO WHERE CORREO='"+(String)it.next()+"' AND ID_PERSONA='"+sIdPersona+"'";
				oConexion.getSentencia().execute(sSentencia);
			}
			// Sentencia para borrar los datos de la persona.
			sSentencia = "DELETE FROM PERSONA WHERE NOMBRES='"+Temp.getNombres()+"' AND APELLIDOS='"+Temp.getApellidos()+"' AND TELEFONO_FIJO="+Temp.getTelefonoFijo()+"";
			oConexion.getSentencia().execute(sSentencia);
			oConexion.getCerrarConexion();
		}
		catch(Exception error) {
			System.out.println("No Se Pudo Retornar Los Valores Consultados Del arreglo Dentro De Eliminar: " + error.getMessage());
		}
	}
}