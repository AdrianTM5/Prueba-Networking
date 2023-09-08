package Prueba;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer 
{
	public static void main(String[] args) 
	{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		try 
		{
			ServerSocket ss = new ServerSocket(5432);
			while(true)
			{
				s = ss.accept();
				System.out.println("Se conectaron desde la IP:"+s.getInetAddress());
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				
				String nom = (String)ois.readObject();
				String saludo = "Oda" + nom+"  " +System.currentTimeMillis();
				oos.writeObject(saludo);
				System.out.println("Saludo enviado");
				
			}
		} 
		catch (IOException | ClassNotFoundException e) 
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
				catch (IOException e) 
				{
					e.printStackTrace();
				}
		}
	}
}
