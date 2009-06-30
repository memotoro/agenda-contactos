package agenda.interfaz;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import agenda.control.*;
/**
 * Clase encargada de contener y dibujar el menu principal.
 * @author Memo Toro
 */
public class MenuPpal extends JPanel{
	/**
	 * Boton de Insertar.
	 */
	public JButton bInsertar;
	/**
	 * Boton de Editar.
	 */
	public JButton bEditar;
	/**
	 * Boton de Eliminar.
	 */
	public JButton bEliminar;
	/**
	 * Boton de Consultar.
	 */
	public JButton bConsultar;
	/**
	 * Label de Insertar.
	 */
	JLabel laInsertar;
	/**
	 * Label de Editar.
	 */
	JLabel laEditar;
	/**
	 * Label de Eliminar.
	 */
	JLabel laEliminar;
	/**
	 * Label de Consultar.
	 */
	JLabel laConsultar;
	/**
	 * Ventana de Despliegue.
	 */
	public Ventana vVentana;
	/**
	 * Variable para Restricciones Gráficas.
	 */
	GridBagConstraints gbc;
	/**
	 * Variable para Distribución de Elementos Gráficos.
	 */
	GridBagLayout gridbag;
	/**
	 * Variable Controladore de Eventos del Menu Principal.
	 */
	public ControladorMenuPpal cmControlador;
	/**
	 * Contructor de la Clase Menu Principal.
	 * Aqui se configuran todos los elementos gráficos.
	 */
	public MenuPpal(){
		// Se configura todo el ambiente de la ventana. Se asigna el controlador de eventos a este menu.
		vVentana = new Ventana(this);
		gridbag = new GridBagLayout();
        setLayout(gridbag);
        setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new TitledBorder("Menu de Opciones")));
		cmControlador = new ControladorMenuPpal(this);
		// Se configuran los botones con su respectivo Listener, mas los otros elementos gráficos.
		laInsertar = new JLabel("Agregar Nueva Persona");
		laInsertar.setFont(new Font("Verdana",Font.BOLD,12));
		laInsertar.setForeground(new Color(30,30,150));
		bInsertar = new JButton("Agregar  !!!");
		bInsertar.addActionListener(cmControlador);
		laEditar = new JLabel("Editar Persona");
		laEditar.setFont(new Font("Verdana",Font.BOLD,12));
		laEditar.setForeground(new Color(30,30,150));
		bEditar= new JButton("Editar !!!");
		bEditar.addActionListener(cmControlador);
		laEliminar = new JLabel("Eliminar Una Persona");
		laEliminar.setFont(new Font("Verdana",Font.BOLD,12));
		laEliminar.setForeground(new Color(30,30,150));
		bEliminar= new JButton("Eliminar !!!");
		bEliminar.addActionListener(cmControlador);
		laConsultar = new JLabel("Buscar Persona");
		laConsultar.setFont(new Font("Verdana",Font.BOLD,12));
		laConsultar.setForeground(new Color(30,30,150));
		bConsultar = new JButton("Buscar !!!");
		bConsultar.addActionListener(cmControlador);
	}
	/**
	 * Método encargado de cargar los elementos gráficos al Panel que se desplegará en la ventana.
	 */
	public void Cargar(){
		gbc = new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(laInsertar,gbc);
        gbc = new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
        add(bInsertar,gbc);
        gbc = new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(laEditar,gbc);
		gbc = new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bEditar,gbc);
		gbc = new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(laEliminar,gbc);
		gbc = new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bEliminar,gbc);
		gbc = new GridBagConstraints(0,3,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(laConsultar,gbc);
		gbc = new GridBagConstraints(1,3,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bConsultar,gbc);
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
	/**
	 * Método Main como Constructor de la aplicación principal.
	 * Inicia un objeto de tipo Menu Principal.
	 */
	public static void main (String [] arg){
		MenuPpal mMenuInicial = new MenuPpal();
		mMenuInicial.Cargar();	
	}
}