import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JScrollBar;

public class FramePrincipal extends JFrame {

	private JPanel principal;
	private JTextField cantidad;
	private JTextField nombre;
	private JTextField precio;
	private JTextField c�digo;
	private JTextField vdrC�d;
	private JTextField vdrCant;
	
	static Connection conexi�n; 
	private JTextField cprC�d;
	private JTextField cprCant;
	private JTextField bscrC�d;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Tienda.abrirConexi�n();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

		/**
	 * Create the frame.
	 */
	public FramePrincipal() {
			getContentPane().setLayout(null);
						
		setForeground(SystemColor.textHighlight);
		setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		setTitle("Mi Supermercado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1041, 614);
		principal = new JPanel();
		principal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(principal);
		principal.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(77, 269, 197, -83);
		principal.add(btnNewButton);
		
		//ETIQUETA DEL T�TULO.
		
		JLabel lblSupermercadoElMarranito = new JLabel("Supermercado El Marranito");
		lblSupermercadoElMarranito.setForeground(new Color(0, 0, 139));
		lblSupermercadoElMarranito.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupermercadoElMarranito.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblSupermercadoElMarranito.setBounds(337, 0, 280, 37);
		principal.add(lblSupermercadoElMarranito);
		
		//PANEL AGREGAR (Contiene todos los elementos gr�ficos para agregar un producto)
		
		JPanel PanelAgregar = new JPanel();
		PanelAgregar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PanelAgregar.setBounds(10, 37, 560, 184);
		principal.add(PanelAgregar);
		PanelAgregar.setLayout(null);
		
		//Panel secundario que contiene los elementos gr�ficos con texto para el men� de agregar.
		
		JPanel datosAgregar = new JPanel();
		datosAgregar.setBounds(12, 11, 393, 159);
		PanelAgregar.add(datosAgregar);
		datosAgregar.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Agregar un nuevo producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		datosAgregar.setLayout(null);
		
		//Texfields para escribir los datos del producto a agregar
		c�digo = new JTextField();
		c�digo.setBounds(97, 41, 242, 20);
		PanelAgregar.add(c�digo);
		c�digo.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(97, 72, 242, 20);
		PanelAgregar.add(nombre);
		nombre.setColumns(10);
		
		precio = new JTextField();
		precio.setBounds(97, 101, 242, 20);
		PanelAgregar.add(precio);
		precio.setColumns(10);
		
		cantidad = new JTextField();
		cantidad.setBounds(86, 120, 242, 20);
		datosAgregar.add(cantidad);
		cantidad.setColumns(10);
		
		
		//Etiquetas del men� agregar.		
		
		JLabel lblc�digo = new JLabel("C\u00F3digo: ");
		lblc�digo.setBounds(10, 25, 67, 28);
		datosAgregar.add(lblc�digo);
		lblc�digo.setForeground(new Color(0, 0, 255));
		lblc�digo.setHorizontalAlignment(SwingConstants.LEFT);
		lblc�digo.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 55, 67, 28);
		datosAgregar.add(lblNombre);
		lblNombre.setForeground(new Color(0, 0, 255));
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(10, 84, 67, 28);
		datosAgregar.add(lblPrecio);
		lblPrecio.setForeground(new Color(0, 0, 255));
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setForeground(Color.BLUE);
		lblCantidad.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblCantidad.setBounds(10, 116, 75, 28);
		datosAgregar.add(lblCantidad);
		
		//TextArea donde aparecer� la lista de productos de la base de datos
		
		JTextArea listaProductos = new JTextArea();
		listaProductos.setBounds(32, 232, 538, 332);
		principal.add(listaProductos);
		List<Producto> misProductos = Tienda.obtenerProductos();
		
		//Inicializar el texto que va dentro del TextArea por defecto (los datos que est�n predefinidos en la base de datos)
		
		listaProductos.setText("");
		
		for (Producto pr : misProductos) {
			listaProductos.append(pr.toString());
			listaProductos.append("\n");
		}
		listaProductos.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		
		//PANEL VENDER 
		
		JPanel panelVender = new JPanel();
		panelVender.setLayout(null);
		panelVender.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelVender.setBounds(580, 50, 433, 157);
		principal.add(panelVender);
		
		// Panel secundario d�nde se encuentran los elementos de texto del panel Vender
		
		JPanel datosVender = new JPanel();
		datosVender.setLayout(null);
		datosVender.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Vender Productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		datosVender.setBounds(12, 11, 393, 96);
		panelVender.add(datosVender);
		
		//Labels del panel vender (etiquetas de texto predefinidas)
		

		JLabel c�dVdr = new JLabel("C\u00F3digo: ");
		c�dVdr.setHorizontalAlignment(SwingConstants.LEFT);
		c�dVdr.setForeground(Color.BLUE);
		c�dVdr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		c�dVdr.setBounds(10, 25, 67, 28);
		datosVender.add(c�dVdr);
				
		JLabel cantVdr = new JLabel("Cantidad:");
		cantVdr.setHorizontalAlignment(SwingConstants.LEFT);
		cantVdr.setForeground(Color.BLUE);
		cantVdr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cantVdr.setBounds(10, 52, 75, 28);
		datosVender.add(cantVdr);
		
		
		//Textfields del panel Vender (entrada de texto por el usuario)
		
		vdrC�d = new JTextField();
		vdrC�d.setColumns(10);
		vdrC�d.setBounds(97, 41, 242, 20);
		panelVender.add(vdrC�d);
		
		vdrCant = new JTextField();
		vdrCant.setColumns(10);
		vdrCant.setBounds(86, 56, 242, 20);
		datosVender.add(vdrCant);
		
		
		//PANEL COMPRAR
		
		JPanel panelComprar = new JPanel();
		panelComprar.setLayout(null);
		panelComprar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelComprar.setBounds(582, 232, 433, 157);
		principal.add(panelComprar);
		
		//Panel Secundario que contiene los objetos de texto del panel comprar 
		
		JPanel comprarDatos = new JPanel();
		comprarDatos.setLayout(null);
		comprarDatos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Comprar Productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		comprarDatos.setBounds(12, 11, 393, 96);
		panelComprar.add(comprarDatos);
		
		//Etiquetas del panel comprar (texto predefinido)
		
		JLabel c�dCpr = new JLabel("C\u00F3digo: ");
		c�dCpr.setHorizontalAlignment(SwingConstants.LEFT);
		c�dCpr.setForeground(Color.BLUE);
		c�dCpr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		c�dCpr.setBounds(10, 25, 67, 28);
		comprarDatos.add(c�dCpr);
		
		JLabel cantCpr = new JLabel("Cantidad:");
		cantCpr.setHorizontalAlignment(SwingConstants.LEFT);
		cantCpr.setForeground(Color.BLUE);
		cantCpr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cantCpr.setBounds(10, 52, 75, 28);
		comprarDatos.add(cantCpr);
		
		//Textfields del panel comprar (texto ingresado por teclado)
		
		cprC�d = new JTextField();
		cprC�d.setColumns(10);
		cprC�d.setBounds(97, 41, 242, 20);
		panelComprar.add(cprC�d);	
		
		cprCant = new JTextField();
		cprCant.setColumns(10);
		cprCant.setBounds(86, 56, 242, 20);
		comprarDatos.add(cprCant);
		
		
		
		//PANEL DE B�SQUEDA
		
		JPanel panelBuscar = new JPanel();
		panelBuscar.setLayout(null);
		panelBuscar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelBuscar.setBounds(580, 407, 433, 129);
		principal.add(panelBuscar);
		
		// Panel secundario con elementos de texto de la b�squeda
		
		JPanel buscarDatos = new JPanel();
		buscarDatos.setLayout(null);
		buscarDatos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Buscar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		buscarDatos.setBounds(12, 11, 393, 65);
		panelBuscar.add(buscarDatos);
		
		//Etiquetas de texto para b�squeda
		

		JLabel c�dBscr = new JLabel("C\u00F3digo: ");
		c�dBscr.setHorizontalAlignment(SwingConstants.LEFT);
		c�dBscr.setForeground(Color.BLUE);
		c�dBscr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		c�dBscr.setBounds(10, 25, 67, 28);
		buscarDatos.add(c�dBscr);
		
		//TextField para entrada de informaci�n por teclado
		
		bscrC�d = new JTextField();
		bscrC�d.setColumns(10);
		bscrC�d.setBounds(97, 41, 242, 20);
		panelBuscar.add(bscrC�d);
		
	
		
		
		
		//L�NEAS DE C�DIGO PARA LOS BOTONES
		
		//Bot�n VENDER y acciones para vender un producto.
		
		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String c�d = vdrC�d.getText(); 
				int cant = Integer.parseInt(vdrCant.getText()); 
				
				
				Tienda.vender(c�d, cant);
				
				vdrC�d.setText("");
				vdrCant.setText("");
				
				JOptionPane.showMessageDialog(null, "El producto se ha vendido. Revise su base de datos.");
				
				
				 
				 List<Producto> misProductos = Tienda.obtenerProductos();
					
					listaProductos.setText("");
					
					for (Producto p : misProductos) {
						listaProductos.append(p.toString());
						listaProductos.append("\n");
					}
				
			}
				
		});
				btnVender.setForeground(new Color(25, 25, 112));
				btnVender.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
				btnVender.setBackground(SystemColor.activeCaption);
				btnVender.setBounds(147, 118, 135, 28);
				panelVender.add(btnVender);
		
		// Bot�n COMPRAR y acciones para comprar un producto.
		
		JButton btnComprar = new JButton("Comprar\r\n");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String c�d = cprC�d.getText(); 
				int cant = -(Integer.parseInt(cprCant.getText())); 
						
				Tienda.vender(c�d, cant);
						
				cprC�d.setText("");
				cprCant.setText("");
						
				JOptionPane.showMessageDialog(null, "El producto se ha comprado. Revise su base de datos.");
						
				//Acualizar base de datos luego de una compra.
				List<Producto> misProductos = Tienda.obtenerProductos();
						
				listaProductos.setText("");
							
				for (Producto p : misProductos) {
								listaProductos.append(p.toString());
								listaProductos.append("\n");
				}
			}
		});
				btnComprar.setForeground(new Color(25, 25, 112));
				btnComprar.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
				btnComprar.setBackground(SystemColor.activeCaption);
				btnComprar.setBounds(147, 118, 135, 28);
				panelComprar.add(btnComprar);
		
		// Bot�n AGREGRAR PRODUCTO y acciones para agregar un producto
		
		JButton btnBotn = new JButton("Agregar ");
		btnBotn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Producto p = new Producto(c�digo.getText(), 
						nombre.getText(), 
						Integer.parseInt(precio.getText()),
						Integer.parseInt(cantidad.getText()));
				
				Tienda.a�adirProducto(p);
				
				c�digo.setText("");
				nombre.setText("");
				precio.setText("");
				cantidad.setText("");	
				
				JOptionPane.showMessageDialog(null, "El producto se ha almacenado en su base de datos");
				
				//Actualiza la lista de productos luego de agregar un producto nuevo. 
				
				List<Producto> misProductos = Tienda.obtenerProductos();
				
				listaProductos.setText("");
				
				for (Producto pr : misProductos) {
					listaProductos.append(pr.toString());
					listaProductos.append("\n");
				}
				
			}
		});
				btnBotn.setBounds(415, 82, 135, 28);
				PanelAgregar.add(btnBotn);
				btnBotn.setBackground(SystemColor.activeCaption);
				btnBotn.setForeground(new Color(25, 25, 112));
				btnBotn.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
				
				//Bot�n BUSCAR y acciones para buscar un producto por su C�DIGO �NICO.
				
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String c�d = bscrC�d.getText(); 
						Producto miProducto;
						try {
							miProducto = Tienda.seleccionarProducto(c�d);
							JOptionPane.showMessageDialog(null, miProducto, "Buscar un producto", JOptionPane.INFORMATION_MESSAGE );
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					}
				});
				btnBuscar.setForeground(new Color(25, 25, 112));
				btnBuscar.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
				btnBuscar.setBackground(SystemColor.activeCaption);
				btnBuscar.setBounds(145, 87, 135, 28);
				panelBuscar.add(btnBuscar);
		
		
		
		
		
	}
}
