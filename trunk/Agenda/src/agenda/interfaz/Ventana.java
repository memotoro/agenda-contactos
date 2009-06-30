package agenda.interfaz;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
/**
 * Clase con el JFrame Principal o Ventana.
 * Como tal es la ventana de despliegue. 
 * @author Memo Toro
 */
public class Ventana extends JFrame{
	/**
	 * Constructor de la Clase Ventana.
	 * Establece las propiedades de dimensiones y características de las ventanas.
	 * @param Temp
	 * Recibe un JPanel como Entrada para poder dibujarse.
	 */
	public Ventana(JPanel Temp){
		getContentPane().add(Temp, BorderLayout.CENTER);
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Agenda de Contactos de Memo Toro" );
	}
}