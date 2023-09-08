package PruebaBytes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServerB 
{
	private static final int BUFFER_LENGTH = 3;
	
	public static void main(String[] args) 
	{
		BufferedReader br = null;
		BufferedWriter bw = null;
		Socket s = null;
		try 
		{
			ServerSocket ss = new ServerSocket(5432);
			while(true)
			{
				s = ss.accept();
				System.out.println("Se conectaron desde la IP: "+s.getInetAddress());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				bw = new BufferedWriter(new PrintWriter(s.getOutputStream()));
				char bEnviar[];
				char bRecive[] = new char[BUFFER_LENGTH];
				StringBuffer sb = new StringBuffer();
				int n;
				while((n = br.read(bRecive)) == BUFFER_LENGTH)
				{
					sb.append(bRecive);
				}
				sb.append(bRecive,0,n);
				String saludo = "Oda "+sb+"  "+System.currentTimeMillis();
				bEnviar = saludo.toCharArray();
				bw.write(bEnviar);
				bw.flush();
				System.out.println("Saludo enviado...");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
				try 
				{
					if(bw != null)bw.close();
					if(br != null)br.close();
					if(s != null)s.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
		}
	}
}
