package agenda.interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import agenda.control.*;
/**
 * Clase encargada de contener y dibujar el menu de editar persona.
 * @author Memo Toro
 */
public class MenuEditar extends JPanel {
	/**
	 * Label Persona.
	 */
	JLabel laPersonas;
	/**
	 * Label del Contador de Personas. 
	 */
	public JLabel laContador;
	/**
	 * Listado de Personas.
	 */
	public List lPersonas;
	/**
	 * Boton de Regresar.
	 */
	public JButton bRegresar;
	/**
	 * Ventana de Despliegue.
	 */
	public JFrame vVentana;
	/**
	 * Variable para Restricciones Gr�ficas.
	 */
	GridBagConstraints gbc;
	/**
	 * Variable para Distribuci�n de Elementos Gr�ficos.
	 */
	GridBagLayout gridbag;
	/**
	 * Variable Controladore de Eventos del Menu Guardar.
	 */
	public ControladorMenuEditar cmControlador;
	/**
	 * Contructor de la Clase Menu Editar.
	 * Aqui se configuran todos los elementos gr�ficos.
	 */
	public MenuEditar(){
		// Se configura todo el ambiente de la ventana. Se asigna el controlador de eventos a este menu.
		vVentana = new Ventana(this);
		gridbag = new GridBagLayout( );
        setLayout(gridbag);
        setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new TitledBorder("Selecci�n de Persona a Editar")));
		cmControlador = new ControladorMenuEditar(this);
		// Se configuran los botones con su respectivo Listener, mas los otros elementos gr�ficos.
		laPersonas = new JLabel("Personas a Editar de la Agenda");
		laPersonas.setFont(new Font("Verdana",Font.BOLD,12));
		laPersonas.setForeground(new Color(30,30,150));
		lPersonas = new List();
		lPersonas.setFont(new Font("Verdana",Font.ITALIC,12));
		lPersonas.addItemListener(cmControlador);
		bRegresar = new JButton("Regresar !!!");
		bRegresar.addActionListener(cmControlador);
		laContador = new JLabel();
		laContador.setFont(new Font("Verdana",Font.BOLD,12));
		laContador.setForeground(new Color(30,30,150));		
		cmControlador.ListadoEditar();
	}
	/**
	 * M�todo encargado de cargar los elementos gr�ficos al Panel que se desplegar� en la ventana.
	 */
	public void Cargar(){
		gbc = new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laPersonas,gbc);
		gbc = new GridBagConstraints(0,1,4,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,300);
		add(lPersonas,gbc);
		gbc = new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bRegresar,gbc);
		gbc = new GridBagConstraints(1,3,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(laContador,gbc);		
	}
	/**
	 * M�todo para dibujar el fondon de la ventana.
	 */
	/*
	public void paint(Graphics Temp){
		Image iFondo = new ImageIcon(getClass().getResource("/azul.jpg")).getImage();
		Temp.drawImage(iFondo, 20, 25, this.getWidth()-40, this.getHeight()-40, this);
		setOpaque(false);
		super.paint(Temp);
	}
	*/
}