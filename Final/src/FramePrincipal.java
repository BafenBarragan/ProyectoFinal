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
	private JTextField código;
	private JTextField vdrCód;
	private JTextField vdrCant;
	
	static Connection conexión; 
	private JTextField cprCód;
	private JTextField cprCant;
	private JTextField bscrCód;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Tienda.abrirConexión();
		
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
		
		//ETIQUETA DEL TÍTULO.
		
		JLabel lblSupermercadoElMarranito = new JLabel("Supermercado El Marranito");
		lblSupermercadoElMarranito.setForeground(new Color(0, 0, 139));
		lblSupermercadoElMarranito.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupermercadoElMarranito.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblSupermercadoElMarranito.setBounds(337, 0, 280, 37);
		principal.add(lblSupermercadoElMarranito);
		
		//PANEL AGREGAR (Contiene todos los elementos gráficos para agregar un producto)
		
		JPanel PanelAgregar = new JPanel();
		PanelAgregar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PanelAgregar.setBounds(10, 37, 560, 184);
		principal.add(PanelAgregar);
		PanelAgregar.setLayout(null);
		
		//Panel secundario que contiene los elementos gráficos con texto para el menú de agregar.
		
		JPanel datosAgregar = new JPanel();
		datosAgregar.setBounds(12, 11, 393, 159);
		PanelAgregar.add(datosAgregar);
		datosAgregar.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Agregar un nuevo producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		datosAgregar.setLayout(null);
		
		//Texfields para escribir los datos del producto a agregar
		código = new JTextField();
		código.setBounds(97, 41, 242, 20);
		PanelAgregar.add(código);
		código.setColumns(10);
		
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
		
		
		//Etiquetas del menú agregar.		
		
		JLabel lblcódigo = new JLabel("C\u00F3digo: ");
		lblcódigo.setBounds(10, 25, 67, 28);
		datosAgregar.add(lblcódigo);
		lblcódigo.setForeground(new Color(0, 0, 255));
		lblcódigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblcódigo.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
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
		
		//TextArea donde aparecerá la lista de productos de la base de datos
		
		JTextArea listaProductos = new JTextArea();
		listaProductos.setBounds(32, 232, 538, 332);
		principal.add(listaProductos);
		List<Producto> misProductos = Tienda.obtenerProductos();
		
		//Inicializar el texto que va dentro del TextArea por defecto (los datos que están predefinidos en la base de datos)
		
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
		
		// Panel secundario dónde se encuentran los elementos de texto del panel Vender
		
		JPanel datosVender = new JPanel();
		datosVender.setLayout(null);
		datosVender.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Vender Productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		datosVender.setBounds(12, 11, 393, 96);
		panelVender.add(datosVender);
		
		//Labels del panel vender (etiquetas de texto predefinidas)
		

		JLabel códVdr = new JLabel("C\u00F3digo: ");
		códVdr.setHorizontalAlignment(SwingConstants.LEFT);
		códVdr.setForeground(Color.BLUE);
		códVdr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		códVdr.setBounds(10, 25, 67, 28);
		datosVender.add(códVdr);
				
		JLabel cantVdr = new JLabel("Cantidad:");
		cantVdr.setHorizontalAlignment(SwingConstants.LEFT);
		cantVdr.setForeground(Color.BLUE);
		cantVdr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cantVdr.setBounds(10, 52, 75, 28);
		datosVender.add(cantVdr);
		
		
		//Textfields del panel Vender (entrada de texto por el usuario)
		
		vdrCód = new JTextField();
		vdrCód.setColumns(10);
		vdrCód.setBounds(97, 41, 242, 20);
		panelVender.add(vdrCód);
		
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
		
		JLabel códCpr = new JLabel("C\u00F3digo: ");
		códCpr.setHorizontalAlignment(SwingConstants.LEFT);
		códCpr.setForeground(Color.BLUE);
		códCpr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		códCpr.setBounds(10, 25, 67, 28);
		comprarDatos.add(códCpr);
		
		JLabel cantCpr = new JLabel("Cantidad:");
		cantCpr.setHorizontalAlignment(SwingConstants.LEFT);
		cantCpr.setForeground(Color.BLUE);
		cantCpr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cantCpr.setBounds(10, 52, 75, 28);
		comprarDatos.add(cantCpr);
		
		//Textfields del panel comprar (texto ingresado por teclado)
		
		cprCód = new JTextField();
		cprCód.setColumns(10);
		cprCód.setBounds(97, 41, 242, 20);
		panelComprar.add(cprCód);	
		
		cprCant = new JTextField();
		cprCant.setColumns(10);
		cprCant.setBounds(86, 56, 242, 20);
		comprarDatos.add(cprCant);
		
		
		
		//PANEL DE BÚSQUEDA
		
		JPanel panelBuscar = new JPanel();
		panelBuscar.setLayout(null);
		panelBuscar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelBuscar.setBounds(580, 407, 433, 129);
		principal.add(panelBuscar);
		
		// Panel secundario con elementos de texto de la búsqueda
		
		JPanel buscarDatos = new JPanel();
		buscarDatos.setLayout(null);
		buscarDatos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Buscar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		buscarDatos.setBounds(12, 11, 393, 65);
		panelBuscar.add(buscarDatos);
		
		//Etiquetas de texto para búsqueda
		

		JLabel códBscr = new JLabel("C\u00F3digo: ");
		códBscr.setHorizontalAlignment(SwingConstants.LEFT);
		códBscr.setForeground(Color.BLUE);
		códBscr.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		códBscr.setBounds(10, 25, 67, 28);
		buscarDatos.add(códBscr);
		
		//TextField para entrada de información por teclado
		
		bscrCód = new JTextField();
		bscrCód.setColumns(10);
		bscrCód.setBounds(97, 41, 242, 20);
		panelBuscar.add(bscrCód);
		
	
		
		
		
		//LÍNEAS DE CÓDIGO PARA LOS BOTONES
		
		//Botón VENDER y acciones para vender un producto.
		
		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cód = vdrCód.getText(); 
				int cant = Integer.parseInt(vdrCant.getText()); 
				
				
				Tienda.vender(cód, cant);
				
				vdrCód.setText("");
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
		
		// Botón COMPRAR y acciones para comprar un producto.
		
		JButton btnComprar = new JButton("Comprar\r\n");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cód = cprCód.getText(); 
				int cant = -(Integer.parseInt(cprCant.getText())); 
						
				Tienda.vender(cód, cant);
						
				cprCód.setText("");
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
		
		// Botón AGREGRAR PRODUCTO y acciones para agregar un producto
		
		JButton btnBotn = new JButton("Agregar ");
		btnBotn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Producto p = new Producto(código.getText(), 
						nombre.getText(), 
						Integer.parseInt(precio.getText()),
						Integer.parseInt(cantidad.getText()));
				
				Tienda.añadirProducto(p);
				
				código.setText("");
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
				
				//Botón BUSCAR y acciones para buscar un producto por su CÓDIGO ÚNICO.
				
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String cód = bscrCód.getText(); 
						Producto miProducto;
						try {
							miProducto = Tienda.seleccionarProducto(cód);
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
