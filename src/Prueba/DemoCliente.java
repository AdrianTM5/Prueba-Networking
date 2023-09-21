package Prueba;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DemoCliente 
{
	public static void main(String[] args) 
	{
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket s = null;
		
		try 
		{
			s = new Socket("192.1680.120",5432);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
			oos.writeObject("Papu");
			
			String ret = (String)ois.readObject();
			
			System.out.println(ret);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
				try 
				{
					if(ois != null) ois.close();
					if(oos != null) oos.close();
					if(s != null) s.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
		}
	}
}
