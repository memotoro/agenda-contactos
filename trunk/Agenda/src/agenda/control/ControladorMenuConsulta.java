package agenda.control;

import java.awt.event.*;
import agenda.interfaz.*;
import agenda.logica.*;
import java.util.List;
import java.util.Iterator;
/**
 * Controlador de Menu Consultar. Implementa ActionListener e ItemListener para eventos de dicho menu.
 * @author Memo Toro
 */
public class ControladorMenuConsulta implements ActionListener,ItemListener {
	/**
	 * Variable de tipo Menu Consulta.
	 */
	MenuConsulta mConsultar;
	/**
	 * Listado de Personas Consultadas.
	 */
	List <Persona> pPosiblesPersonas;
	/**
	 * Variable para consultar la base de datos.
	 */
	Consultar cListaPersonas;
	/**
	 * Variable de Tipo Menu Guardar.
	 */
	MenuGuardar mSeleccion;
	/**
	 * Variable para recorre List.
	 */
	Iterator it;
	/**
	 * Contructor del Controlador Menu Consulta.
	 * @param Temp
	 * Recibe el Menu Consulta y lo asigna para trabajarlo como referencia.
	 */
	public ControladorMenuConsulta(MenuConsulta Temp){
		mConsultar=Temp;
	}
	/**
	 * Método de Evento de Boton Consultar
	 */
	public void actionPerformed(ActionEvent evento){
		// Si el boton fue consultar.
		if((evento.getSource()==mConsultar.bConsulta)){			
			cListaPersonas = new Consultar();
			// Crea el arreglo con los valores de consulta y el campo a consultar.
			String [] sCampoConsulta = {mConsultar.lCampos.getSelectedItem(),mConsultar.tBusqueda.getText()};
			// Lo envía a la Clase de tipo Consultar.
			pPosiblesPersonas = cListaPersonas.Buscar(sCampoConsulta);
			mConsultar.lConsultas.removeAll();
			it = pPosiblesPersonas.iterator();
			Persona Temp = new Persona();
			// Carga la lista con las personas consultadas.
			while(it.hasNext()){
				Temp = (Persona)it.next();
				mConsultar.lConsultas.add(Temp.getNombres()+"  "+Temp.getApellidos());
			}
			// Asigna un label con el número total de personas en la lista. Un contador.
			mConsultar.laContador.setText("Personas Encontradas: " + mConsultar.lConsultas.getItemCount());
		}
		// Si el boton fue regresar. Eliminar el menu.
		if((evento.getSource()==mConsultar.bRegresar)){
			mConsultar.vVentana.dispose();
		}
	}
	/**
	 * Método de Evento de Selección.
	 * @exception No se pudo desplegar la persona.
	 */
	public void itemStateChanged(ItemEvent evento){
		// Si selecciona una persona de la lista, se despliega en el menu guardar.
		try{
			mSeleccion = new MenuGuardar();
			mSeleccion.cmControlador.desplieguePersona(pPosiblesPersonas.get(mConsultar.lConsultas.getSelectedIndex()));
			// Ejecuta el método de bloquear los elementos del menu guardar.
			mSeleccion.cmControlador.bloquearMenu();
			mSeleccion.Cargar();
		}
		catch(Exception error) {
			System.out.println("No se pudo Retornar los valores consultados del arreglo." + error.getMessage());
		}
	}
}
