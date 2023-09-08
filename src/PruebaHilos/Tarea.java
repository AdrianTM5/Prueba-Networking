package PruebaHilos;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Tarea extends Thread //Corre con hilos
{
	private Socket s = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public Tarea(Socket socket)
	{
		this.s = socket;
	}
	
	public void run()
	{
		try 
		{
			System.out.println("Se conectaron desde la IP: "+s.getInetAddress());
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			String nom = (String)ois.readObject();
			String saludo = "Oda "+nom+"  "+System.currentTimeMillis();
			oos.writeObject(saludo);
			System.out.println("Saludo enviado...");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(oos != null) oos.close();
				if(ois != null) ois.close();
				if(s != null) s.close();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
	}
}
