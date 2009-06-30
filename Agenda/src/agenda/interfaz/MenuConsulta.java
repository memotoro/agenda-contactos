package agenda.interfaz;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import agenda.control.*;
/**
 * 
 * @author Memo Toro
 */
public class MenuConsulta extends JPanel{
	/**
	 * Boton de Consultar.
	 */
	public JButton bConsulta;
	/**
	 * Boton de Regresar.
	 */
	public JButton bRegresar;
	/**
	 * Label de busqueda.
	 */
	JLabel laBusqueda;
	/**
	 * Label de Campos.
	 */
	JLabel laCampos;
	/**
	 * Label del Contador de Personas.
	 */
	public JLabel laContador;
	/**
	 * Listado de Selección para los Campos.
	 */
	public Choice lCampos;
	/**
	 * Área de Texto para el Criterio de Busqueda.
	 */
	public JTextField tBusqueda;
	/**
	 * Listado de Personas Consultadas.
	 */
	public List lConsultas;
	/**
	 * Ventana de Despliegue.
	 */
	public JFrame vVentana;
	/**
	 * Variable para Restricciones Gráficas.
	 */
	GridBagConstraints gbc;
	/**
	 * Variable para Distribución de Elementos Gráficos.
	 */
	GridBagLayout gridbag;
	/**
	 * Variable Controladore de Eventos del Menu Eliminar.
	 */
	public ControladorMenuConsulta cmControlador;
	/**
	 * Contructor de la Clase Menu Consultar.
	 * Aqui se configuran todos los elementos gráficos.
	 */
	public MenuConsulta(){
		// Se configura todo el ambiente de la ventana. Se asigna el controlador de eventos a este menu.
		vVentana = new Ventana(this);
		gridbag = new GridBagLayout( );
        setLayout(gridbag);
        setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new TitledBorder("Busqueda de Personas")));
        cmControlador = new ControladorMenuConsulta(this);
		// Se configuran los botones con su respectivo Listener, mas los otros elementos gráficos.
		laBusqueda = new JLabel("Valor de Busqueda:");
		laBusqueda.setFont(new Font("Verdana",Font.BOLD,12));
		laBusqueda.setForeground(new Color(30,30,150));
		tBusqueda = new JTextField(30);
		laCampos = new JLabel("Criterio de Busqueda:");
		laCampos.setFont(new Font("Verdana",Font.BOLD,12));
		laCampos.setForeground(new Color(30,30,150));
		lCampos= new Choice();
		lCampos.add("NOMBRES");
		lCampos.add("APELLIDOS");
		lCampos.add("TELEFONO_FIJO");
		lCampos.add("TELEFONO_CELULAR");
		lCampos.add("CORREO");
		laContador = new JLabel();
		laContador.setFont(new Font("Verdana",Font.BOLD,12));
		laContador.setForeground(new Color(30,30,150));	
		bConsulta = new JButton("Buscar Persona!!!");
		bConsulta.addActionListener(cmControlador);
		bRegresar = new JButton("Regresar !!!");
		bRegresar.addActionListener(cmControlador);
		lConsultas = new List();
		lConsultas.setFont(new Font("Verdana",Font.ITALIC,12));
		lConsultas.addItemListener(cmControlador);
	}
	/**
	 * Método encargado de cargar los elementos gráficos al Panel que se desplegará en la ventana.
	 */	
	public void Cargar(){
		gbc = new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laBusqueda,gbc);
		gbc = new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(tBusqueda,gbc);
		gbc = new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laCampos,gbc);
		gbc = new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(lCampos,gbc);
		gbc = new GridBagConstraints(0,3,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laContador,gbc);
		gbc = new GridBagConstraints(2,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bConsulta,gbc);
		gbc = new GridBagConstraints(2,3,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bRegresar,gbc);
		gbc = new GridBagConstraints(0,4,3,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,250);
		add(lConsultas,gbc);
	}
	/**
	 * Método para dibujar el fondon de la ventana.
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