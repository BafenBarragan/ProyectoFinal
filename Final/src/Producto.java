
public class Producto {

		
		private String código; 
		private String nombre; 
		private int precio;
		private int cantidad; 
		 
		
		public Producto(String cód, String nom, int pre, int can) {
			
			this.código = cód; 
			this.nombre = nom; 
			this.precio = pre; 
			this.cantidad = can; 
			 
		}
		
		public void cambiarCódigo(String cód) {
			
			this.nombre = cód; 
		}
		
		public String obtenerCódigo() {
			
			return this.código;
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
			return "- " + código + "   " + nombre + "   $" + precio + ".   (" + cantidad + " units)" ;
		}
		
}		
		
		
	
