package agenda.control;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import agenda.interfaz.*;
import agenda.logica.*;

import java.util.List;
import java.util.Iterator;
/**
 * Clase controlador de menu editar. Implementa ActionListener e ItemListener para eventos de dicho menu.
 * @author Memo Toro
 */
public class ControladorMenuEditar implements ActionListener, ItemListener{
	/**
	 * Variable de tipo menu editar.
	 */
	MenuEditar mEditar;
	/**
	 * Variable entera para verificación.
	 */
	int iOpcion;
	/**
	 * Variable para Password de usuario.
	 */
	String sPassword;
	/**
	 * Variable para sentencia de consulta de autenticación.
	 */
	String sAutenticar;
	/**
	 * Variable de conexión a la base de datos.
	 */
	OperacionBD oConexion;
	/**
	 * Variable para Consultar.
	 */
	Consultar cListaPersonas;
	/**
	 * Listado de Personas a Editar. 
	 */
	List <Persona> pPosiblesPersonas;
	/**
	 * Menu Emergente de confirmación.
	 */
	JOptionPane dConfirmar;
	/**
	 * Dialogo Emergente para autenticar.
	 */
	JDialog dAutenticar;
	/**
	 * Área de texto para el password de usuario.
	 */
	JTextField tPass;
	/**
	 * Label de Password
	 */
	JLabel lPass;
	/**
	 * Variable de tipo Menu Guardar.
	 */
	MenuGuardar mEdicion;
	/**
	 * Variable para recorrer List.
	 */
	Iterator it;
	/**
	 * Contructor del COntrolador Menu Editar.
	 * Inicializa la conexión y el menu.
	 * @param Temp
	 * Menu Editar como referencia para los eventos y elementos del menu.
	 */
	public ControladorMenuEditar(MenuEditar Temp){
		mEditar = Temp;
		oConexion= new OperacionBD();
	}
	/**
	 * Método que lista las personas a editar.
	 * Se listan todas las personas de la base de datos.
	 */
	public void ListadoEditar(){
		try{
			cListaPersonas = new Consultar();
			// Envia la cadena de consulta como todos los nombres posibles.
			String [] sCampoConsulta = {"NOMBRES",""};
			pPosiblesPersonas = cListaPersonas.Buscar(sCampoConsulta);
			mEditar.lPersonas.removeAll();
			it  = pPosiblesPersonas.iterator();
			Persona Temp = new Persona();
			// Carga la lista con las personas consultadas.
			while(it.hasNext()){
				Temp = (Persona)it.next();
				mEditar.lPersonas.add(Temp.getNombres()+"  "+Temp.getApellidos());
			}
			// Asigna un label con el número total de personas en la lista. Un contador.
			mEditar.laContador.setText("Personas en la Agenda: " + mEditar.lPersonas.getItemCount());
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
		if((evento.getSource()==mEditar.bRegresar)){
			mEditar.vVentana.dispose();
		}
	}
	/**
	 * Método de Evento de seleccion sobre la lista de personas a editar.
	 */
	public void itemStateChanged(ItemEvent evento){
		// Creación del menu emergente de confirmación de edición de la persona seleccionada.
		dConfirmar = new JOptionPane();
		iOpcion = dConfirmar.showConfirmDialog(mEditar,"¿Está Seguro de Editar los Datos de la Persona en la Agenda?","Editar !!!",JOptionPane.YES_OPTION);
		dConfirmar.setSize(350,100);
		dConfirmar.setLocation(500,300);
		dConfirmar.setVisible(true);
		// Validación de la opción seleccionada en el menu emergente. En este caso, confirmación de editar persona.
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
			dAutenticar.setLocationRelativeTo(mEditar);
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
						// Consulta sobre la base de datos el rol ingresado para editar.
				    	sAutenticar = "SELECT ROLNAME FROM PG_ROLES WHERE ROLNAME='"+sPassword+"'";
				    	oConexion.setResultado(sAutenticar);
				    	// Si existe resultado de autenticación
				    	if(oConexion.getResultado().next()){
							mEdicion = new MenuGuardar();
				    		// Envia la persona a editar al menu Guardar con sus datos para despliegue.
							mEdicion.cmControlador.desplieguePersona(pPosiblesPersonas.get(mEditar.lPersonas.getSelectedIndex()));
							mEdicion.bBoton=mEdicion.bEditar;
							mEdicion.Cargar();
							oConexion.getCerrarConexion();
							mEditar.vVentana.dispose();
				    	}
				    	// Si no existe rol de edición ingresado.
				    	else{
				    		// Mensaje Emergente de notificación.
				    		dConfirmar = new JOptionPane();
				    		dConfirmar.showMessageDialog(mEditar,"No Se puede Editar la Persona de la Agenda: Usuario Incorrecto");
				    		dConfirmar.setSize(350,200);
				    		dConfirmar.setLocation(500,300);
				    		dConfirmar.setVisible(true);
				    		mEditar.vVentana.dispose();
				    	}
					}
					catch(Exception error) {
						System.out.println("No se pudo Retornar los valores consultados del arreglo." + error.getMessage());
					}
					}
				});
		}
		// Si se decide no editar la persona.
		if(iOpcion == JOptionPane.NO_OPTION){
			dConfirmar.removeAll();		
		}
	}
}