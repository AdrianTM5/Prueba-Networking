package PruebaBytes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoClienteB 
{
	private static final int BUFFER_LENGTH = 3;
	
	public static void main(String[] args) 
	{
		BufferedReader br = null;
		BufferedWriter bw = null;
		Socket s = null;
		
		try 
		{
			s = new Socket("192.168.0.120",5432);
			bw = new BufferedWriter(new PrintWriter(s.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			char bEnvia[] = "Pablo".toCharArray();
			char bRecibe[] = new char[BUFFER_LENGTH];
			bw.write(bEnvia);
			bw.flush();
			
			StringBuffer sb = new StringBuffer();
			int n;
			while((n = br.read(bRecibe)) == BUFFER_LENGTH)
			{
				sb.append(bRecibe);
			}
			sb.append(bRecibe,0,n);
			
			System.out.println(sb);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(br != null)br.close();
				if(bw != null)bw.close();
				if(s != null)s.close();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
	}
}
