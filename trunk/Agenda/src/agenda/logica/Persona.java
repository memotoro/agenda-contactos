package agenda.logica;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Esta es la Clase Persona. Encargada de Administrar la
 * informaci�n relacionada con una Persona en la Agenda.
 * @author Memo Toro  
 */
public class Persona {
	/**
	 * Nombres de la Perons
	 */
	private String sNombres;
	/**
	 * Apellidos de la Persona
	 */	
	private String sApellidos;
	/**
	 * Numero de Telefono
	 */	
	private Long lTelefonoFijo;
	/**
	 * Listado de Celulares
	 */
	private List <Long> lCelulares;
	/**
	 * Listado de Correos 
	 */
	private List <String> sCorreos;
	/**
	 * Constructor de la Persona.
	 * Ayuda a Inicializar los Listados. 
	 */
	public Persona(){
		lCelulares = new ArrayList<Long>();
		sCorreos = new ArrayList<String>();
	}
	/**
	 * Funci�n que inicializa el valor del Nombre de la Persona.
	 * @param Temp
	 * Recibe los Nombres y los Almacena en la Persona.
	 */
	public void setNombres(String Temp){
		sNombres = Temp.toUpperCase();
	}
	/**
	 * Funci�n que inicializa el valor de los Apellidos de la Persona.
	 * @param Temp
	 * Recibe los Apellidos y los Almacena en la Persona.
	 */
	public void setApellidos(String Temp){
		sApellidos = Temp.toUpperCase();
	}
	/**
	 * Funci�n que asigna el numero de Telefono Fijo de una Persona.
	 * @param Temp
	 * Recibe el N�mero de Telefono y lo Almacena en la Persona.
	 */
	public void setTelefonoFijo(String Temp){
		lTelefonoFijo = Long.valueOf(Temp).longValue();
	}
	/**
	 * Funci�n que asigna el listado de numeros celulares de la persona.
	 * @param Temp
	 * Recibe el listado de Celulares y los Almacena como Listado.
	 */
	public void setCelulares(List <Long> Temp){
		Iterator<Long> it = Temp.iterator();
		while(it.hasNext())
			lCelulares.add(it.next());
	}
	/**
	 * Funci�n que asigna el Listado de Correos de una Persona.
	 * @param Temp
	 * Recibe el Listado de Correos y lo Almacena como Listado.
	 */
	public void setCorreos(List <String> Temp){
		Iterator<String> it = Temp.iterator();
		while(it.hasNext())
			sCorreos.add(it.next());		
	}
	/**
	 * Funci�n que retorna los Nombres de una Persona.
	 * @return String con los Nombres de la Persona.
	 */
	public String getNombres(){
		return sNombres;
	}
	/**
	 * Funci�n que retorna los Apellidos de una Persona.
	 * @return String los Apellidos de la Persona.
	 */
	public String getApellidos(){
		return sApellidos;
	}
	/**
	 * Funci�n que retorna el N�mero de Telefono Fijo de una Persona.
	 * @return Long con el Numero de Telefono Fijo de la Persona.
	 */
	public long getTelefonoFijo(){
		return lTelefonoFijo;
	}
	/**
	 * Funci�n que retorna el Listado de Celulares de una Persona.
	 * @return List con los Celulares que tenga la Persona.
	 */
	public List <Long> getCelulares(){
		return lCelulares;
	}
	/**
	 * Funci�n que retorna el Listado de Correos de una Persona.
	 * @return List con los Correos de la Persona.
	 */
	public List <String> getCorreos(){
		return sCorreos;
	}
}