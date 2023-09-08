package PruebaHilos;

import java.net.ServerSocket;
import java.net.Socket;

public class DemoServerMT //Corre con hilos
{
	public static void main(String[] args) throws Exception
	{
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		
		while(true)
		{
			try 
			{
				s = ss.accept();
				(new Tarea(s)).start();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
