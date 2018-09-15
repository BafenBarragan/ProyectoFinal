import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;


/** Administra la información de la tienda y la base de datos
 * 
 * @author Bafen Ricardo Barragán Sánchez
 *
 */
public class Tienda {
	
	
	//Para trabajar con la base de datos.
	
	static Connection conexión; 
	
	public static void abrirConexión() {
		try {
			Class.forName("org.h2.Driver");
			conexión = DriverManager.getConnection("jdbc:h2:c:/temp2/tienda", "sa", "");
		}
		catch (Exception ex) {
			conexión = null; 
			System.err.println("Error de conexión con la BD");
			ex.printStackTrace();
		}
	}
	
	public static void cerrarConexión() {
		try {
			if (conexión != null) {
				conexión.close();
			}
		}
			catch(Exception ex) {}
	}
	
	//Para añadir información a la BD
	
	public static void añadirProducto (Producto p) {
		PreparedStatement ps; 
		String sql  = "INSERT INTO PRODUCTO VALUES(?, ?, ?, ?)"; //String con la instrucción propia de la BD.
		
		try {
			ps = conexión.prepareStatement(sql); 
			ps.setString(1, p.obtenerCódigo()); 
			ps.setString(2, p.obtenerNombre());
			ps.setInt(3, p.obtenerPrecio());
			ps.setInt(4, p.obtenerCantidad());
			
			ps.executeUpdate();		
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}					
	}

	
public static Producto seleccionarProducto(String cód) throws Exception{
		
		
			String sql = "SELECT NOMBRE, PRECIO, CANTIDAD FROM PRODUCTO WHERE CÓDIGO = '" + cód+ "'"; //String con la instrucción propia de la BD.
			
			Statement st = conexión.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			
			if (rs.next()) {
				
				String nombre = rs.getString(1);
				int precio = rs.getInt(2);
				int cantidad = rs.getInt(3);
				
				Producto p = new Producto (cód, nombre, precio, cantidad); 
				
				rs.close();
				st.close();
				return p; 
			}
			else {
				rs.close();
				st.close();
				throw new Exception("No existe un producto con el código ingresado.");
			}
			
		}
		
		public static void vender (String codigo, int cantidad)
		{
			try {
				Statement st = conexión.createStatement();
				
				String sql = "UPDATE PRODUCTO SET CANTIDAD = CANTIDAD - " + cantidad + "WHERE CÓDIGO = '" +codigo+"'";
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
				Statement st = conexión.createStatement();
				
				String sql = "UPDATE PRODUCTO SET CANTIDAD = CANTIDAD + " + (cantidad) + "WHERE CÓDIGO = '" + codigo +"'";
				st.executeUpdate(sql); 
				
				st.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
	
	// Para obtener información de la BD.
	
	public static List<Producto> obtenerProductos(){
		List<Producto> productos = new ArrayList<>();
		try {
			Statement st; 
			String sql = "SELECT * FROM PRODUCTO"; //String con la instrucción propia de la BD.
			
			st = conexión.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			
			while (rs.next()) {
				String código = rs.getString("CÓDIGO");
				String nombre = rs.getString("NOMBRE");
				int precio = rs.getInt("PRECIO"); 
				int cantidad = rs.getInt("CANTIDAD");
				
				Producto producto = new Producto(código, nombre, precio, cantidad);
				
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
	
