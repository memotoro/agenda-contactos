package agenda.control;

import java.awt.event.*;
import agenda.interfaz.*;
/**
 * Clase Controladora de Menu Principal. Implementa el ActionListener para responder a los eventos de dicho menu.
 * @author Memo Toro
 */
public class ControladorMenuPpal implements ActionListener {
	/**
	 * Variable de Tipo Menu Principal para hacer referencia al menu cargado.
	 */
	MenuPpal mPpal;
	/**
	 * Constructor de la Clase Menu Principal.
	 * Trabaja como referencia y asigna el Menu Principal cargado.
	 * @param Temp
	 */
	public ControladorMenuPpal(MenuPpal Temp){
		mPpal=Temp;
	}
	/**
	 * Método de Eventos sobre el Menu Principal.
	 */
	public void actionPerformed(ActionEvent evento){
		// Si se hizo clic en el boton de insertar, se carga un menu guardar.
		if(evento.getSource()==mPpal.bInsertar){
			MenuGuardar mNuevo = new MenuGuardar();
			mNuevo.bBoton=mNuevo.bGuardar;
			mNuevo.Cargar();
		}
		// Si se hizo clic sobre el boton de editar, se carga un menu editar.
		if(evento.getSource()==mPpal.bEditar){
			MenuEditar mEdicion = new MenuEditar();
			mEdicion.Cargar();
		}
		// Si se hizo clic sobre el boton de eliminar, se carga un menu eliminar.
		if(evento.getSource()==mPpal.bEliminar){
			MenuEliminar mEliminar = new MenuEliminar();
			mEliminar.Cargar();
		}
		// Si se hizo clic sobre el boton de consulta, se carga un menu consultar.
		if(evento.getSource()==mPpal.bConsultar){
			MenuConsulta mConsulta = new MenuConsulta();
			mConsulta.Cargar();	
		}		
	}
}