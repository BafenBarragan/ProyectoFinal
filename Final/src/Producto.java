
public class Producto {

		
		private String c�digo; 
		private String nombre; 
		private int precio;
		private int cantidad; 
		 
		
		public Producto(String c�d, String nom, int pre, int can) {
			
			this.c�digo = c�d; 
			this.nombre = nom; 
			this.precio = pre; 
			this.cantidad = can; 
			 
		}
		
		public void cambiarC�digo(String c�d) {
			
			this.nombre = c�d; 
		}
		
		public String obtenerC�digo() {
			
			return this.c�digo;
		}
		
		public void cambiarNombre(String nom) {
			
			this.nombre = nom; 
		}
		
		public String obtenerNombre() {
			
			return this.nombre;
		}
		
		public void cambiarPrecio(int pre) {
			
			this.precio = pre; 
		}
		
		public int obtenerPrecio() {
			
			return this.precio;
		}
		
		public void cambiarCantidad(int can) {
			
			this.cantidad = can; 
		}
		
		public int obtenerCantidad() {
			
			return this.cantidad;
		}
		

		@Override
		public String toString() {
			return "- " + c�digo + "   " + nombre + "   $" + precio + ".   (" + cantidad + " units)" ;
		}
		
}		
		
		
	
