import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;


/** Administra la informaci�n de la tienda y la base de datos
 * 
 * @author Bafen Ricardo Barrag�n S�nchez
 *
 */
public class Tienda {
	
	
	//Para trabajar con la base de datos.
	
	static Connection conexi�n; 
	
	public static void abrirConexi�n() {
		try {
			Class.forName("org.h2.Driver");
			conexi�n = DriverManager.getConnection("jdbc:h2:c:/temp2/tienda", "sa", "");
		}
		catch (Exception ex) {
			conexi�n = null; 
			System.err.println("Error de conexi�n con la BD");
			ex.printStackTrace();
		}
	}
	
	public static void cerrarConexi�n() {
		try {
			if (conexi�n != null) {
				conexi�n.close();
			}
		}
			catch(Exception ex) {}
	}
	
	//Para a�adir informaci�n a la BD
	
	public static void a�adirProducto (Producto p) {
		PreparedStatement ps; 
		String sql  = "INSERT INTO PRODUCTO VALUES(?, ?, ?, ?)"; //String con la instrucci�n propia de la BD.
		
		try {
			ps = conexi�n.prepareStatement(sql); 
			ps.setString(1, p.obtenerC�digo()); 
			ps.setString(2, p.obtenerNombre());
			ps.setInt(3, p.obtenerPrecio());
			ps.setInt(4, p.obtenerCantidad());
			
			ps.executeUpdate();		
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}					
	}

	
public static Producto seleccionarProducto(String c�d) throws Exception{
		
		
			String sql = "SELECT NOMBRE, PRECIO, CANTIDAD FROM PRODUCTO WHERE C�DIGO = '" + c�d+ "'"; //String con la instrucci�n propia de la BD.
			
			Statement st = conexi�n.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			
			if (rs.next()) {
				
				String nombre = rs.getString(1);
				int precio = rs.getInt(2);
				int cantidad = rs.getInt(3);
				
				Producto p = new Producto (c�d, nombre, precio, cantidad); 
				
				rs.close();
				st.close();
				return p; 
			}
			else {
				rs.close();
				st.close();
				throw new Exception("No existe un producto con el c�digo ingresado.");
			}
			
		}
		
		public static void vender (String codigo, int cantidad)
		{
			try {
				Statement st = conexi�n.createStatement();
				
				String sql = "UPDATE PRODUCTO SET CANTIDAD = CANTIDAD - " + cantidad + "WHERE C�DIGO = '" +codigo+"'";
				st.executeUpdate(sql); 
				
				st.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
	 
		public static void comprar (String codigo, int cantidad)
		{
			try {
				Statement st = conexi�n.createStatement();
				
				String sql = "UPDATE PRODUCTO SET CANTIDAD = CANTIDAD + " + (cantidad) + "WHERE C�DIGO = '" + codigo +"'";
				st.executeUpdate(sql); 
				
				st.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
	
	// Para obtener informaci�n de la BD.
	
	public static List<Producto> obtenerProductos(){
		List<Producto> productos = new ArrayList<>();
		try {
			Statement st; 
			String sql = "SELECT * FROM PRODUCTO"; //String con la instrucci�n propia de la BD.
			
			st = conexi�n.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			
			while (rs.next()) {
				String c�digo = rs.getString("C�DIGO");
				String nombre = rs.getString("NOMBRE");
				int precio = rs.getInt("PRECIO"); 
				int cantidad = rs.getInt("CANTIDAD");
				
				Producto producto = new Producto(c�digo, nombre, precio, cantidad);
				
				productos.add(producto); 
				
			}
			
			rs.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return productos; 
		
	}
	
	
	
	
}	
	
