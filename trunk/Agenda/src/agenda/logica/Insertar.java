package agenda.logica;

import java.sql.SQLException;
import java.util.Iterator;
/**
 * Clase encargada de realizar los Insert sobre la base de datos.
 * @author Memo Toro
 */
public class Insertar {
	/**
	 * Sentencia con la orden de insertar.
	 */
	String sSentencia;
	/**
	 * Variable para establecer la Conexión con la base de datos.
	 */
	OperacionBD oConexion;
	/** 
	 * Variable para recorrer los diferentes List.
	 */
	Iterator it;
	/**
	 * Constructor de la clase Insertar.
	 * Inicializa la conexión con la base de datos.
	 */
	public Insertar(){
		oConexion = new OperacionBD();
	}
	/**
	 * Inserta los valores en la base de datos a partir de una Persona que recibe.
	 * @param Temp
	 * Persona que recibe el metodo para que pueda ser alamcenada en la base de datos.
	 * @return boolean Indicando si se almaceno o no la persona en la base de datos.
	 * @exception La persona no se pudo alamcenar en la base de datos.
	 */
	public boolean InsertarDatos(Persona Temp){
		try{
			// Crea la sentencia de INSERT sobre la tabla Persona
			sSentencia = "INSERT INTO PERSONA(NOMBRES,APELLIDOS,TELEFONO_FIJO) VALUES('"+Temp.getNombres()+"','"+Temp.getApellidos()+"','"+Temp.getTelefonoFijo()+"')";
			oConexion.getSentencia().execute(sSentencia);
			it = Temp.getCelulares().iterator();
			// Crea la sentencia para Celulares y se tiene un bucle para alamecenar varios celulares.
			while(it.hasNext()){
				sSentencia = "INSERT INTO CELULAR(TELEFONO_CELULAR,ID_PERSONA) VALUES('"+(Long)it.next()+"',(SELECT currval('persona_id_seq')))";
				oConexion.getSentencia().execute(sSentencia);
			}	
			it = Temp.getCorreos().iterator();
			// Crea la sentencia para Correos y se tiene un bucle para alamecenar varios correos.
			while(it.hasNext()){
				sSentencia = "INSERT INTO CORREO(CORREO,ID_PERSONA) VALUES('"+(String)it.next()+"',(SELECT currval('persona_id_seq')))";
				oConexion.getSentencia().execute(sSentencia);
			}
			oConexion.getCerrarConexion();
			return true;
		}
		catch(SQLException error) {
			System.out.println( "No Se Pudo Insertar Los Datos En La Base De Datos: " + error.getMessage());
			return false;
		}		
	}	
}