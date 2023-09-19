package ChatServer;

public class LanzadorServer 
{
	public static void main(String[] args) 
	{
		ModeloServer ms = new ModeloServer();
		VistaServer vs = new VistaServer();
		VistaServerLoginA vsl = new VistaServerLoginA();
		
		ControladorServer co = new ControladorServer(ms, vs, vsl);
	}
}