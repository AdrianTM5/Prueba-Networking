package ChatCliente;

public class LanzadorCliente 
{
	public static void main(String[] args) 
	{
		ModeloCliente mc = new ModeloCliente();
		VistaCliente vc = new VistaCliente();
		VistaClienteLogin vcl = new VistaClienteLogin();
		
		ControladorCliente co = new ControladorCliente(mc, vc, vcl);
	}
}