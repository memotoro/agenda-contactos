package agenda.interfaz;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import agenda.control.*;
/**
 * Clase encargada de contener y dibujar el menu de guardar persona.
 * @author Memo Toro
 */
public class MenuGuardar extends JPanel{
	/**
	 * Área de Texto para Nombres.
	 */
	public JTextField tNombres; 
	/**
	 * Área de Texto para Apellidos.
	 */
	public JTextField tApellidos;
	/**
	 * Área de Texto para Telefono.
	 */
	public JTextField tTelefono;
	/**
	 * Área de Texto para Celular
	 */
	public JTextField tCelular; 
	/**
	 * Área de Texto para Correo.
	 */
	public JTextField tCorreo;
	/**
	 * Listado de Celulares.
	 */
	public List lCelulares;
	/**
	 * Listado de Correos
	 */
	public List lCorreos;
	/**
	 * Label de Nombres.
	 */
	JLabel laNombres; 
	/**
	 * Label de Apellidos.
	 */
	JLabel laApellidos;
	/**
	 * Label de Telefono Fijo.
	 */
	JLabel laTelefono;
	/**
	 * Label de Ceular.
	 */
	JLabel laCelular; 
	/**
	 * Label de Celulares.
	 */
	JLabel laCelulares;
	/**
	 * Label de Correo.
	 */
	JLabel laCorreo;
	/**
	 * Label de Correos.
	 */
	JLabel laCorreos;
	/**
	 * Boton de Guardar.
	 */
	public JButton bGuardar;
	/**
	 * Boton de Editar.
	 */
	public JButton bEditar;
	/**
	 * Boton para Guardar o Editar.
	 */
	public JButton bBoton;
	/**
	 * Boton de Regresar.
	 */
	public JButton bRegresar;
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
	 * Variable Controladore de Eventos del Menu Guardar.
	 */
	public ControladorMenuGuardar cmControlador;
	/**
	 * Contructor de la Clase Menu Guardar.
	 * Aqui se configuran todos los elementos gráficos.
	 */
	public MenuGuardar(){
		// Se configura todo el ambiente de la ventana. Se asigna el controlador de eventos a este menu.
		vVentana = new Ventana(this);
		gridbag = new GridBagLayout();
        setLayout(gridbag);
        setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new TitledBorder("Información Personal")));
		cmControlador = new ControladorMenuGuardar(this);
		// Se configuran los botones con su respectivo Listener, mas los otros elementos gráficos.
		laNombres = new JLabel("Nombres:*");
		laNombres.setFont(new Font("Verdana",Font.BOLD,12));
		laNombres.setForeground(new Color(30,30,150));
		tNombres = new JTextField(20); 
		tNombres.setFont(new Font("Verdana",Font.ITALIC,12));
		laApellidos = new JLabel("Apellidos:*");
		laApellidos.setFont(new Font("Verdana",Font.BOLD,12));
		laApellidos.setForeground(new Color(30,30,150));
		tApellidos = new JTextField(20);
		tApellidos.setFont(new Font("Verdana",Font.ITALIC,12));
		laTelefono = new JLabel("Telefono Fijo:");
		laTelefono.setFont(new Font("Verdana",Font.BOLD,12));
		laTelefono.setForeground(new Color(30,30,150));
		tTelefono = new JTextField(7);
		tTelefono.setFont(new Font("Verdana",Font.ITALIC,12));
		tTelefono.addKeyListener(cmControlador);	
		laCelular = new JLabel("Celular(es):");
		laCelular.setFont(new Font("Verdana",Font.BOLD,12));
		laCelular.setForeground(new Color(30,30,150));
		tCelular = new JTextField(10);
		tCelular.setFont(new Font("Verdana",Font.ITALIC,12));
		tCelular.addActionListener(cmControlador);
		tCelular.addKeyListener(cmControlador);
		tCelular.setToolTipText("Digite ENTER para ingresar número a la lista de celulares");
		laCelulares = new JLabel("Listado:");
		laCelulares.setFont(new Font("Verdana",Font.BOLD,12));
		laCelulares.setForeground(new Color(30,30,150));
		lCelulares = new List();
		lCelulares.setFont(new Font("Verdana",Font.ITALIC,12));
		lCelulares.addItemListener(cmControlador);
		laCorreo = new JLabel("Correo(s):");
		laCorreo.setForeground(new Color(30,30,150));
		laCorreo.setFont(new Font("Verdana",Font.BOLD,12));
		tCorreo = new JTextField(20);
		tCorreo.setFont(new Font("Verdana",Font.ITALIC,12));
		tCorreo.addActionListener(cmControlador);
		tCorreo.setToolTipText("Digite ENTER para ingresar correo a la lista de correos");
		laCorreos = new JLabel("Listado:");
		laCorreos.setFont(new Font("Verdana",Font.BOLD,12));
		laCorreos.setForeground(new Color(30,30,150));
		lCorreos = new List();
		lCorreos.setFont(new Font("Verdana",Font.ITALIC,12));
		lCorreos.addItemListener(cmControlador);
		bGuardar = new JButton("Grabar !!!");
		bGuardar.addActionListener(cmControlador);
		bEditar = new JButton("Actualizar !!!");
		bEditar.addActionListener(cmControlador);
		bBoton = new JButton();
		bBoton.addActionListener(cmControlador);
		bRegresar = new JButton("Regresar !!!");
		bRegresar.addActionListener(cmControlador);
	}
	/**
	 * Método encargado de cargar los elementos gráficos al Panel que se desplegará en la ventana.
	 */
	public void Cargar(){
		gbc = new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laNombres,gbc);
		gbc = new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(tNombres,gbc);
		gbc = new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laApellidos,gbc);
		gbc = new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(tApellidos,gbc);
		gbc = new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laTelefono,gbc);
		gbc = new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(tTelefono,gbc);
		gbc = new GridBagConstraints(0,3,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laCelular,gbc);
		gbc = new GridBagConstraints(1,3,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(tCelular,gbc);
		gbc = new GridBagConstraints(1,4,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laCelulares,gbc);
		gbc = new GridBagConstraints(1,5,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,20);
		add(lCelulares,gbc);
		gbc = new GridBagConstraints(0,6,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laCorreo,gbc);
		gbc = new GridBagConstraints(1,6,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(tCorreo,gbc);
		gbc = new GridBagConstraints(1,7,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0);
		add(laCorreos,gbc);
		gbc = new GridBagConstraints(1,8,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,20);
		add(lCorreos,gbc);
		gbc = new GridBagConstraints(2,9,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bBoton,gbc);
		gbc = new GridBagConstraints(2,10,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),10,10);
		add(bRegresar,gbc);
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
	