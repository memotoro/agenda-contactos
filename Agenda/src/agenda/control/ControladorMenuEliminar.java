package agenda.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import agenda.interfaz.*;
import agenda.logica.*;

import java.util.List;
import java.util.Iterator;
/**
 * Clase controladora del menu de Eliminar. Implementa ActionListener e ItemListener para los eventos de dicho menu.
 * @author Memo Toro
 */
public class ControladorMenuEliminar implements ActionListener, ItemListener{
	/**
	 * Variable de tipo menu eliminar.
	 */
	MenuEliminar mEliminar;
	/**
	 * Vartiable para el Password.
	 */
	String sPassword;
	/**
	 * Variable para sentencia de consulta de autenticación.
	 */
	String sAutenticar;
	/**
	 * Entero de verificación de opción del menu emergente.
	 */
	int iOpcion;
	/**
	 * Variable de tipo Consultar para personas a eliminar.
	 */
	Consultar cListaPersonas;
	/**
	 * Listado de Posibles personas a eliminar.
	 */
	List <Persona> pPosiblesPersonas;
	/**
	 * Variable de conexión a la base de datos.
	 */
	OperacionBD oConexion;
	/**
	 * Variable de tipo Eliminar.
	 */
	Eliminar eSeleccionado;
	/**
	 * Menu Emergente de confirmación.
	 */
	JOptionPane dConfirmar;
	/**
	 * Dialogo para autenticar.
	 */
	JDialog dAutenticar;
	/**
	 * Área de texto para ingresar el password.
	 */
	JTextField tPass;
	/**
	 * Label de Password.
	 */
	JLabel lPass;
	/**
	 * Variable para recorreo List.
	 */
	Iterator it;
	/**
	 * Constructor de clase de Controlador Menu Eliminar.
	 * Inicializar la conexión y el menu.
	 * @param Temp
	 * Variable de tipo Menu Eliminar para trabajar como referencia el menu que disparo el evento.
	 */	
	public ControladorMenuEliminar(MenuEliminar Temp){
		mEliminar = Temp;
		oConexion= new OperacionBD();
	}
	/**
	 * Método que construye el listado de posibles personas a borrar.
	 * Como tal, realiza la consulta de todas las personas de la lista.s
	 * @exception No se puede cargar las personas a la lista.
	 */
	public void ListadoBorrar(){
		try{
			cListaPersonas = new Consultar();
			// Envia la cadena de consulta como todos los nombres posibles.
			String [] sCampoConsulta = {"NOMBRES",""};
			pPosiblesPersonas = cListaPersonas.Buscar(sCampoConsulta);
			mEliminar.lPersonas.removeAll();
			it = pPosiblesPersonas.iterator();
			Persona Temp = new Persona();
			// Carga la lista con las personas consultadas.
			while(it.hasNext()){
				Temp = (Persona)it.next();
				mEliminar.lPersonas.add(Temp.getNombres()+"  "+Temp.getApellidos());
			}
			// Asigna un label con el número total de personas en la lista. Un contador.
			mEliminar.laContador.setText("Personas en la Agenda: " + mEliminar.lPersonas.getItemCount());
		}
		catch(Exception error) {
			System.out.println("No se pudo Retornar los valores consultados." + error.getMessage());
		}
	}
	/** 
	 * Método de Evento de Boton Regresar para limpiar el menu.
	 */
	public void actionPerformed(ActionEvent evento){
		// Si es regresar, elimina el menu.
		if((evento.getSource()==mEliminar.bRegresar)){
			mEliminar.vVentana.dispose();
		}
	}
	/**
	 * Método de Evento de seleccion sobre la lista de personas a borrar.
	 */
	public void itemStateChanged(ItemEvent evento){
		// Creación del menu emergente de confirmación de borrar la persona seleccionada.
		dConfirmar = new JOptionPane();
		iOpcion = dConfirmar.showConfirmDialog(mEliminar,"¿Está Seguro de Borrar la Persona de la Agenda?","Eliminar !!!",JOptionPane.YES_OPTION);
		dConfirmar.setSize(350,150);
		dConfirmar.setLocation(500,300);
		dConfirmar.setVisible(true);
		// Validación de la opción seleccionada en el menu emergente. En este caso, confirmación de eliminar persona.
		if(iOpcion == JOptionPane.YES_OPTION){
			// Creación del menu emergente de autenticación de usuario.
			dAutenticar = new JDialog();
			lPass = new JLabel("Ingrese Nombre de Usuario:");
			tPass = new JPasswordField (20);
			dAutenticar.setLayout(new FlowLayout());
			dAutenticar.setTitle("Autenticación !!!");
			dAutenticar.add(lPass);
			dAutenticar.add(tPass);
			dAutenticar.setSize(300,100);
			dAutenticar.setLocationRelativeTo(mEliminar);
			dAutenticar.setVisible(true);
			// Controlador implementado para el menu emergente de autenticación.
			tPass.addActionListener(new ActionListener(){	
				/**
				 * Método de validación de confirmación de contraseña.
				 */
				public void actionPerformed(ActionEvent evento){
					try{				    		
						sPassword = tPass.getText().toString();
						dAutenticar.dispose();
						// Consulta sobre la base de datos el rol ingresado para borrar.
				    	sAutenticar = "SELECT ROLNAME FROM PG_ROLES WHERE ROLNAME='"+sPassword+"'";
				    	oConexion.setResultado(sAutenticar);
				    	// Si existe resultado de autenticación
				    	if(oConexion.getResultado().next()){
				    		eSeleccionado = new Eliminar();
				    		// Elimina la persona seleccionada conel objeto Eliminar.
				    		eSeleccionado.Borrar(pPosiblesPersonas.get(mEliminar.lPersonas.getSelectedIndex()));
				    		oConexion.getCerrarConexion();
				    		dConfirmar = new JOptionPane();
				    		// Mensaje Emergente de notificación.
				    		dConfirmar.showMessageDialog(mEliminar,"Persona Borrada Satisfactoriamente !!!");
				    		dConfirmar.setSize(350,100);
				    		dConfirmar.setLocation(500,300);
				    		dConfirmar.setVisible(true);
				    		mEliminar.vVentana.dispose();
				    	}
				    	// Si no existe rol de eliminación ingresado.
				    	else{
				    		// Mensaje Emergente de notificación.
				    		dConfirmar = new JOptionPane();
				    		dConfirmar.showMessageDialog(mEliminar,"No Se puede Borrar la Persona de la Agenda: Usuario Incorrecto");
				    		dConfirmar.setSize(350,200);
				    		dConfirmar.setLocation(500,300);
				    		dConfirmar.setVisible(true);
				    		mEliminar.vVentana.dispose();
				    	}
					}
					catch(Exception error) {
						System.out.println("No se pudo Retornar los valores consultados del arreglo." + error.getMessage());
					}
					}
				});
		}
		// Si se decide no eliminar la persona.
		if(iOpcion == JOptionPane.NO_OPTION){
			dConfirmar.removeAll();		
		}
	}	
}