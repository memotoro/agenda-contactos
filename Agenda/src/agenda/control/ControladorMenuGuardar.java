package agenda.control;

import java.awt.event.*;
import javax.swing.*;
import agenda.interfaz.*;
import agenda.logica.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Clase controladora de los eventos de Menu Guardar. Implementa ActionListener, KeyListener e ItemListener para los diferentes
 * eventos de dicha menu.
 * @author Memo Toro
 */
public class ControladorMenuGuardar implements ActionListener, KeyListener, ItemListener{
	/**
	 * Variable de tipo Menu Guardar.
	 */
	MenuGuardar mInsertar;
	/**
	 * Persona a Ingresar.
	 */
	Persona pNuevaPersona;
	/**
	 * Persona para referencia al actualizar.
	 */
	Persona pCopiaPersona;
	/**
	 * Variable de tipo insertar para guardar datos.
	 */
	Insertar iNuevoRegistro;
	/**
	 * Variable de tipo Editar para actualizar datos.
	 */
	Editar eNuevoRegistro;
	/**
	 * Panel Emergente de confirmación.
	 */
	JOptionPane dConfirmacion;
	/**
	 * Variable para recorrer List.
	 */
	Iterator it;
	/**
	 * Constructor de la Clase Controlador de Menu Principal.
	 * @param Temp
	 * Recibe como parametro un menu guardar para trabajarlo por referencia.
	 */
	public ControladorMenuGuardar(MenuGuardar Temp){
		mInsertar=Temp;
	}
	/**
	 * Método para verificar la completitud de los campos obligatorios en el menu.
	 * @return boolean para saber si se tienen todos o no lo campos.
	 */
	public boolean verificarCampos(){
		boolean bVerificacion;
		if(mInsertar.tNombres.getText().equalsIgnoreCase("") || mInsertar.tApellidos.getText().equalsIgnoreCase(""))
			bVerificacion=false;
		else{
			if(mInsertar.tTelefono.getText().equalsIgnoreCase("") || mInsertar.tTelefono.getText().equalsIgnoreCase("0"))
				mInsertar.tTelefono.setText("0000000");
			bVerificacion=true;
		}
		return bVerificacion;
	}
	/**
	 * Método que no permite la edición de los contenidos en el menu cuando se carga desde la opción de consultar.
	 */
	public void bloquearMenu(){
		mInsertar.tNombres.setEditable(false);
		mInsertar.tApellidos.setEditable(false);;
		mInsertar.tTelefono.setEditable(false);
		mInsertar.tCelular.setVisible(false);
		mInsertar.lCelulares.removeItemListener(mInsertar.cmControlador);
		mInsertar.tCorreo.setVisible(false);
		mInsertar.lCorreos.removeItemListener(mInsertar.cmControlador);
		mInsertar.bBoton.setVisible(false);
	}
	/**
	 * Método que se encarga de tomar una Persona y distribuir su información en los elementos gráficos del menu.
	 * @param Temp 
	 * Persona a desplegar como consulta, edicion.
	 * @exception La persona no se pudo asignar correctamente a los elementos gráficos.
	 */
	public void desplieguePersona(Persona Temp){
		try{
			pCopiaPersona = Temp;
			mInsertar.tNombres.setText(Temp.getNombres());
			mInsertar.tApellidos.setText(Temp.getApellidos());
			mInsertar.tTelefono.setText(String.valueOf(Temp.getTelefonoFijo()));
			it = Temp.getCelulares().iterator();
			while(it.hasNext())
				mInsertar.lCelulares.add(String.valueOf(it.next()));
			it = Temp.getCorreos().iterator();
			while(it.hasNext())
				mInsertar.lCorreos.add((String)it.next());
		}
		catch(Exception error) {
			System.out.println("No se pudo Retornar los valores consultados del despliegue." + error.getMessage());
		}
	}
	/**
	 * Método que crea una persona para poderla almacenar.
	 * Toma todos los elementos gráficos y crea un Objeto tipo Persona.
	 */
	public void creacionPersona(){
		pNuevaPersona = new Persona();
		pNuevaPersona.setNombres(mInsertar.tNombres.getText());
		pNuevaPersona.setApellidos(mInsertar.tApellidos.getText());
		pNuevaPersona.setTelefonoFijo(mInsertar.tTelefono.getText().substring(0,7));
		List <Long> TempCelulares = new ArrayList<Long>();
		for(int i=0;i<mInsertar.lCelulares.getItemCount();i++)
			TempCelulares.add(Long.valueOf(mInsertar.lCelulares.getItem(i)).longValue());
		pNuevaPersona.setCelulares(TempCelulares);
		List <String> TempCorreos = new ArrayList<String>();
		for(int i=0;i<mInsertar.lCorreos.getItemCount();i++)
			TempCorreos.add(mInsertar.lCorreos.getItem(i));
		pNuevaPersona.setCorreos(TempCorreos);
	}
	/**
	 * Método de Eventos del Menu Guardar.
	 * Discrimina si se activo Guardar o Actualizar, y dependiendo de dichos botones, ejecuta operaciones diferentes sobre
	 * la persona cargada en el menu.
	 */
	public void actionPerformed(ActionEvent evento){
		// Si el boton seleccionado fue guardar. 
		if(evento.getSource()==mInsertar.bGuardar){
			// Si no se tienen todos los campos llenos.
			if(!verificarCampos()){
				dConfirmacion = new JOptionPane();
				dConfirmacion.showMessageDialog(mInsertar,"Faltan Datos Obligatorios (*) para Guardar la Persona !!!");	
			}
			// Si se tienen los campos llenos.
			if(verificarCampos()){
				creacionPersona();
				iNuevoRegistro = new Insertar();
				// Inserta los Datos en la base de datos con el objeto tipo Insetar.
				boolean bGuardado = iNuevoRegistro.InsertarDatos(pNuevaPersona);
				// Si se guardo satisfactoriamente
				if(bGuardado){
					dConfirmacion = new JOptionPane();
					dConfirmacion.showMessageDialog(mInsertar,"La Persona se ha Agregado en la Agenda !!!");
					mInsertar.vVentana.dispose();
				}
				// SI hubo error al guardar.
				else{
					dConfirmacion = new JOptionPane();
					dConfirmacion.showMessageDialog(mInsertar,"No se Pudo Agregar a la Persona en la Agenda !!!","ERROR DE ALMACENAMIENTO",JOptionPane.ERROR_MESSAGE);
				}
				// Elimina el menu.
				mInsertar.removeAll();	
			}
		}
		// Si el boton seleccionado fue editar. 
		if(evento.getSource()==mInsertar.bEditar){
			// Si no se tienen todos los campos llenos.
			if(!verificarCampos()){
				dConfirmacion = new JOptionPane();
				dConfirmacion.showMessageDialog(mInsertar,"Faltan Datos Obligatorios (*) para Guardar la Persona !!!");	
			}
			// Si se tienen todos los campos llenos.
			if(verificarCampos()){
				creacionPersona();
				eNuevoRegistro = new Editar();
				// Actualiza los Datos en la base de datos con el objeto tipo Editar.
				boolean bGuardado = eNuevoRegistro.ActualizarDatos(pNuevaPersona,pCopiaPersona);
				// Si se actualizó satisfactoriamente
				if(bGuardado){
					dConfirmacion = new JOptionPane();
					dConfirmacion.showMessageDialog(mInsertar,"La Persona se ha Actualizado en la Agenda !!!");
					mInsertar.vVentana.dispose();
				}
				// SI hubo error al actualizar.
				else{
					dConfirmacion = new JOptionPane();
					dConfirmacion.showMessageDialog(mInsertar,"No se Pudo Actualizar la Persona en la Agenda !!!","ERROR DE ALMACENAMIENTO",JOptionPane.ERROR_MESSAGE);
				}
				// Elimina el menu.
				mInsertar.removeAll();	
			}
		}
		// Evento para regresar al menu anterior.
		if((evento.getSource()==mInsertar.bRegresar)){
			mInsertar.vVentana.dispose();
		}
		// Evento para cargar la lista de celulares con un Enter.
		if((evento.getSource()==mInsertar.tCelular)){
			mInsertar.lCelulares.add(mInsertar.tCelular.getText().substring(0,10));
			mInsertar.lCelulares.setEnabled(true);
			mInsertar.tCelular.setText("");
			}
		// Evento para cargar la lista de correos con un Enter.
		if((evento.getSource()==mInsertar.tCorreo)){
			mInsertar.lCorreos.add(mInsertar.tCorreo.getText());
			mInsertar.lCorreos.setEnabled(true);
			mInsertar.tCorreo.setText("");
		}
	}	
	/**
	 * Método para eventos de texto
	 */
	public void itemStateChanged(ItemEvent evento){
		// Evento para limpiar la lista de celualres con un Enter.
		if(evento.getSource()==mInsertar.lCelulares){
			mInsertar.tCelular.setText(mInsertar.lCelulares.getSelectedItem());
			mInsertar.lCelulares.remove(mInsertar.lCelulares.getSelectedIndex());
		}
		// Evento para limpiar la lista de correos con un Enter.
		if(evento.getSource()==mInsertar.lCorreos){
			mInsertar.tCorreo.setText(mInsertar.lCorreos.getSelectedItem());
			mInsertar.lCorreos.remove(mInsertar.lCorreos.getSelectedIndex());
		}	
	}
	/**
	 * Método de eventos de texto
	 */
	public void keyTyped(KeyEvent evento){
		// Verifica que para los campos de celular y telefono fijo solo entre números.
		char caracter = evento.getKeyChar();
		if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE))
			evento.consume();
	}
	/**
	 * Métodos implementados por requerimiento de la interface KeyListener
	 */
	public void keyReleased(KeyEvent e){;}
	/**
	 * Métodos implementados por requerimiento de la interface KeyListener
	 */
	public void keyPressed(KeyEvent e){;}
}