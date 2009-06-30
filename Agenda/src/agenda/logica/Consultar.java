package agenda.logica;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Clase encargada de realizar las consultas de personas sobre la base de datos.
 * @author Memo Toro
 */
public class Consultar{
	/**
	 * Lista de Posibles personas consultadas. 
	 */
	List <Persona> pPosibles;
	/**
	 * Listado de identificadores de las personas que coincidan con la busqueda. 
	 */
	List <String> sIdPersona;
	/**
	 * Listado de correos de la Persona. 
	 */
	List <String> sCorreos;
	/**
	 * Listado de celulares de la Persona.
	 */
	List <Long> lCelular;
	/**
	 * Variable para almacenar la sentecia de consulta SQL.
	 */
	String sSentenciaConsulta;
	/**
	 * Variable con el nombre de la tabla.
	 */
	String sTabla;
	/**
	 * Variable con el nombre del campo a consultar.
	 */
	String sCampo;
	/**
	 * Variable con el valor criterio de consulta.
	 */
	String sValor;
	/**
	 * Variable para establecer la conexón.
	 */
	OperacionBD oConexion;
	/**
	 * Variable para recorrer los List.
	 */
	Iterator it;
	/**
	 * Constructor de la Clase Consultar.
	 * Inicializar la Conexión, los Listados como ArrayList.
	 */
	public Consultar(){
		oConexion = new OperacionBD();
		pPosibles = new ArrayList<Persona>();
		sIdPersona = new ArrayList<String>();
		sCorreos = new ArrayList();
		lCelular = new ArrayList();		
	}
	/**
	 * Método encargado de consultar la base de datos y devolver el listado de personas que coinciden con los criterios de busqueda.
	 * @param Temp
	 * Arreglo de String [] que contiene el campo a consultar y el valor para consultar.
	 * @return List con las Personas posibles consultadas que coinciden con los criterios de busqueda.
	 */
	public List <Persona> Buscar(String [] Temp){
		try{
			// Se alamacenan los valores del arreglo
			sCampo=Temp[0];
			sValor=Temp[1];
			// se Discrimina las tablas según los campos a consultar.
			if(sCampo.equalsIgnoreCase("NOMBRES") || sCampo.equalsIgnoreCase("APELLIDOS") || sCampo.equalsIgnoreCase("TELEFONO_FIJO")){
				sTabla="PERSONA";
				sValor=sValor.toUpperCase();
			}
			if(sCampo.equalsIgnoreCase("TELEFONO_CELULAR"))
				sTabla="CELULAR";
			if(sCampo.equalsIgnoreCase("CORREO")){
				sTabla="CORREO";
				sValor=sValor.toLowerCase();
			}
			// Se construye la sentencia para obtener el Id de la persona en las diferentes tablas consultadas.
			sSentenciaConsulta="SELECT ID_PERSONA FROM "+sTabla+" WHERE "+sCampo+" LIKE '%"+sValor+"%' GROUP BY ID_PERSONA ORDER BY ID_PERSONA ASC";
			oConexion.setResultado(sSentenciaConsulta);
			// Bucle que almacena todos los posibles Id de las personas que coinciden con los criterios de consulta.
			while(oConexion.getResultado().next())
				sIdPersona.add(oConexion.getResultado().getString("ID_PERSONA"));
			it = sIdPersona.iterator();
			// Bucle que consulta en detalla cada tabla a partir de cada Id obtenido. Adicionalmente crea el listado de las personas.
			while(it.hasNext()){
				Persona pTemp = new Persona();
				sValor = (String)it.next();
				// Consulta sobre la tabla de Persona.
				sSentenciaConsulta="SELECT * FROM PERSONA WHERE ID_PERSONA='"+sValor+"'";
				oConexion.setResultado(sSentenciaConsulta);
				oConexion.getResultado().next();	
				pTemp.setNombres(oConexion.getResultado().getString("NOMBRES"));
				pTemp.setApellidos(oConexion.getResultado().getString("APELLIDOS"));
				pTemp.setTelefonoFijo(oConexion.getResultado().getString("TELEFONO_FIJO"));
				// Consulta sobre la tabla de Celular.
				sSentenciaConsulta="SELECT * FROM CELULAR WHERE ID_PERSONA='"+sValor+"'";
				oConexion.setResultado(sSentenciaConsulta);
				// Bucle para cargar el List de Celulares con lo encontrado como celulares de la persona.
				while(oConexion.getResultado().next())
					lCelular.add(Long.valueOf(oConexion.getResultado().getString("TELEFONO_CELULAR")).longValue());
				pTemp.setCelulares(lCelular);
				// Se limpian los List para cada persona.
				lCelular.clear();
				// Consulta sobre la tabla de Correo
				sSentenciaConsulta="SELECT * FROM CORREO WHERE ID_PERSONA='"+sValor+"'";
				oConexion.setResultado(sSentenciaConsulta);
				// Bucle para cargar el List de Correos con lo encontrado como Correos de la persona.
				while(oConexion.getResultado().next())
					sCorreos.add(oConexion.getResultado().getString("CORREO"));	
				pTemp.setCorreos(sCorreos);
				// Se limpian los List para cada persona.
				sCorreos.clear();
				// Se carga cada persona construida con la información consultada en el Listado que se retornará.
				pPosibles.add(pTemp);
			}
			// Ordenación del Listado de Personas en Orden Alfabético con el método de Burbuja.
			for(int i=0;i<pPosibles.size();i++){
				for(int j=0;j<pPosibles.size();j++){
					if((pPosibles.get(i).getNombres() + pPosibles.get(i).getApellidos()).compareTo(pPosibles.get(j).getNombres()+pPosibles.get(j).getApellidos())<0){
						Persona PersonaPaso = pPosibles.get(i);
						pPosibles.set(i, pPosibles.get(j));		
						pPosibles.set(j, PersonaPaso);
					}
				}
			}
		}
		catch(Exception error) {
			System.out.println("No Se Pudo Retornar Los Valores Consultados En ResultSet: " + error.getMessage());
			pPosibles = null;
		}
	return pPosibles;
	}	
}